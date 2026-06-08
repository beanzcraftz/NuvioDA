import os
import re

base_dir = r"C:\STYFF\Apps\NuvioDA\NuvioMobile\composeApp\src\desktopMain\kotlin\com\nuvio\app"

def process_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()

    if "actual fun" not in content:
        return

    # Only process files that have empty implementations
    if "{ }" not in content and "= null" not in content and "= emptyList()" not in content and "= false" not in content and '""' not in content:
        return

    # Skip files that are intentionally stubbed
    filename = os.path.basename(filepath)
    if filename in ["ExternalPlayerPlatform.desktop.kt", "PlayerPlatformEffects.desktop.kt", "ProfileHoverHapticFeedback.desktop.kt"]:
        return

    # Inject java.util.prefs.Preferences
    if "java.util.prefs.Preferences" not in content:
        content = content.replace("package ", "package ", 1) # dummy
        # Find imports block
        if "import " in content:
            content = content.replace("import ", "import java.util.prefs.Preferences\nimport ", 1)
        else:
            # no imports
            content = re.sub(r'(package [^\n]+)', r'\1\n\nimport java.util.prefs.Preferences', content, 1)

    # Inject preferences node inside the actual object
    if "private val preferences" not in content:
        content = re.sub(r'(actual object [A-Za-z0-9_]+ \{)', r'\1\n    private val preferences = Preferences.userRoot().node("nuvio_desktop")', content)

    # Replace loadPayload(): String? = null
    content = re.sub(r'actual fun loadPayload\(\):\s*String\?\s*=\s*null', r'actual fun loadPayload(): String? = preferences.get("payload", null)', content)
    # Replace savePayload(payload: String) { }
    content = re.sub(r'actual fun savePayload\(payload:\s*String\)\s*\{\s*\}', r'actual fun savePayload(payload: String) { preferences.put("payload", payload) }', content)
    # Replace removePayload(key: String) { }
    content = re.sub(r'actual fun removePayload\(key:\s*String\)\s*\{\s*\}', r'actual fun removePayload(key: String) { preferences.remove("payload_$key") }', content)
    # Replace loadPayload(key: String): String? = null
    content = re.sub(r'actual fun loadPayload\(key:\s*String\):\s*String\?\s*=\s*null', r'actual fun loadPayload(key: String): String? = preferences.get("payload_$key", null)', content)
    # Replace savePayload(key: String, payload: String) { }
    content = re.sub(r'actual fun savePayload\(key:\s*String,\s*payload:\s*String\)\s*\{\s*\}', r'actual fun savePayload(key: String, payload: String) { preferences.put("payload_$key", payload) }', content)
    
    # Replace loadPayload(profileId: Int): String? = null
    content = re.sub(r'actual fun loadPayload\(profileId:\s*Int\):\s*String\?\s*=\s*null', r'actual fun loadPayload(profileId: Int): String? = preferences.get("payload_$profileId", null)', content)
    # Replace savePayload(profileId: Int, payload: String) { }
    content = re.sub(r'actual fun savePayload\(profileId:\s*Int,\s*payload:\s*String\)\s*\{\s*\}', r'actual fun savePayload(profileId: Int, payload: String) { preferences.put("payload_$profileId", payload) }', content)
    # Replace removePayload(profileId: Int) { }
    content = re.sub(r'actual fun removePayload\(profileId:\s*Int\)\s*\{\s*\}', r'actual fun removePayload(profileId: Int) { preferences.remove("payload_$profileId") }', content)
    
    # Generic generic replacements for specific names like loadSelectedTheme, saveSelectedTheme
    # loadXYZ(): String? = null
    def load_string_replacer(match):
        method_name = match.group(1)
        return f'actual fun {method_name}(): String? = preferences.get("{method_name}", null)'
    content = re.sub(r'actual fun ([a-zA-Z0-9_]+)\(\):\s*String\?\s*=\s*null', load_string_replacer, content)

    # saveXYZ(value: String) { }
    def save_string_replacer(match):
        method_name = match.group(1)
        param_name = match.group(2)
        return f'actual fun {method_name}({param_name}: String) {{ preferences.put("{method_name}", {param_name}) }}'
    content = re.sub(r'actual fun ([a-zA-Z0-9_]+)\(([a-zA-Z0-9_]+):\s*String\)\s*\{\s*\}', save_string_replacer, content)

    # loadXYZ(): Boolean? = null
    def load_boolean_replacer(match):
        method_name = match.group(1)
        return f'actual fun {method_name}(): Boolean? = preferences.get("{method_name}", null)?.toBooleanStrictOrNull()'
    content = re.sub(r'actual fun ([a-zA-Z0-9_]+)\(\):\s*Boolean\?\s*=\s*null', load_boolean_replacer, content)

    # saveXYZ(value: Boolean) { }
    def save_boolean_replacer(match):
        method_name = match.group(1)
        param_name = match.group(2)
        return f'actual fun {method_name}({param_name}: Boolean) {{ preferences.put("{method_name}", {param_name}.toString()) }}'
    content = re.sub(r'actual fun ([a-zA-Z0-9_]+)\(([a-zA-Z0-9_]+):\s*Boolean\)\s*\{\s*\}', save_boolean_replacer, content)

    # loadXYZ(): Int? = null
    def load_int_replacer(match):
        method_name = match.group(1)
        return f'actual fun {method_name}(): Int? = preferences.get("{method_name}", null)?.toIntOrNull()'
    content = re.sub(r'actual fun ([a-zA-Z0-9_]+)\(\):\s*Int\?\s*=\s*null', load_int_replacer, content)

    # saveXYZ(value: Int) { }
    def save_int_replacer(match):
        method_name = match.group(1)
        param_name = match.group(2)
        return f'actual fun {method_name}({param_name}: Int) {{ preferences.put("{method_name}", {param_name}.toString()) }}'
    content = re.sub(r'actual fun ([a-zA-Z0-9_]+)\(([a-zA-Z0-9_]+):\s*Int\)\s*\{\s*\}', save_int_replacer, content)

    # loadXYZ(): Float? = null
    def load_float_replacer(match):
        method_name = match.group(1)
        return f'actual fun {method_name}(): Float? = preferences.get("{method_name}", null)?.toFloatOrNull()'
    content = re.sub(r'actual fun ([a-zA-Z0-9_]+)\(\):\s*Float\?\s*=\s*null', load_float_replacer, content)

    # saveXYZ(value: Float) { }
    def save_float_replacer(match):
        method_name = match.group(1)
        param_name = match.group(2)
        return f'actual fun {method_name}({param_name}: Float) {{ preferences.put("{method_name}", {param_name}.toString()) }}'
    content = re.sub(r'actual fun ([a-zA-Z0-9_]+)\(([a-zA-Z0-9_]+):\s*Float\)\s*\{\s*\}', save_float_replacer, content)

    # clearXYZ() { }
    def clear_replacer(match):
        method_name = match.group(1)
        return f'actual fun {method_name}() {{ preferences.remove("{method_name}") }}'
    content = re.sub(r'actual fun ([a-zA-Z0-9_]+)\(\)\s*\{\s*\}', clear_replacer, content)

    # loadXYZ(): Boolean = false
    def load_boolean_strict_replacer(match):
        method_name = match.group(1)
        return f'actual fun {method_name}(): Boolean = preferences.getBoolean("{method_name}", false)'
    content = re.sub(r'actual fun ([a-zA-Z0-9_]+)\(\):\s*Boolean\s*=\s*false', load_boolean_strict_replacer, content)

    # BingeGroupCacheStorage.desktop.kt specific
    content = re.sub(r'actual fun load\(hashedKey:\s*String\):\s*String\?\s*=\s*null', r'actual fun load(hashedKey: String): String? = preferences.get("binge_$hashedKey", null)', content)
    content = re.sub(r'actual fun save\(hashedKey:\s*String,\s*value:\s*String\)\s*\{\s*\}', r'actual fun save(hashedKey: String, value: String) { preferences.put("binge_$hashedKey", value) }', content)
    content = re.sub(r'actual fun remove\(hashedKey:\s*String\)\s*\{\s*\}', r'actual fun remove(hashedKey: String) { preferences.remove("binge_$hashedKey") }', content)
    
    # StreamLinkCacheStorage.desktop.kt specific
    content = re.sub(r'actual fun loadEntry\(hashedKey:\s*String\):\s*String\?\s*=\s*null', r'actual fun loadEntry(hashedKey: String): String? = preferences.get("stream_$hashedKey", null)', content)
    content = re.sub(r'actual fun saveEntry\(hashedKey:\s*String,\s*payload:\s*String\)\s*\{\s*\}', r'actual fun saveEntry(hashedKey: String, payload: String) { preferences.put("stream_$hashedKey", payload) }', content)
    content = re.sub(r'actual fun removeEntry\(hashedKey:\s*String\)\s*\{\s*\}', r'actual fun removeEntry(hashedKey: String) { preferences.remove("stream_$hashedKey") }', content)
    
    with open(filepath, 'w', encoding='utf-8') as f:
        f.write(content)

for root, dirs, files in os.walk(base_dir):
    for file in files:
        if file.endswith(".desktop.kt"):
            process_file(os.path.join(root, file))
