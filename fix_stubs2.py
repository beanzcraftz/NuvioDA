import os
import re

base_dir = r"C:\STYFF\Apps\NuvioDA\NuvioMobile\composeApp\src\desktopMain\kotlin\com\nuvio\app"

def process_file(filepath):
    with open(filepath, 'r', encoding='utf-8') as f:
        content = f.read()

    changed = False
    
    # ProfilePinCacheStorage
    if "loadPayload(profileIndex: Int): String? = null" in content:
        content = content.replace('actual fun loadPayload(profileIndex: Int): String? = null', 'actual fun loadPayload(profileIndex: Int): String? = preferences.get("payload_$profileIndex", null)')
        changed = True
    if "savePayload(profileIndex: Int, payload: String) { }" in content:
        content = content.replace('actual fun savePayload(profileIndex: Int, payload: String) { }', 'actual fun savePayload(profileIndex: Int, payload: String) { preferences.put("payload_$profileIndex", payload) }')
        changed = True

    # ResumePromptStorage
    if "saveLastPlayerVideoId(videoId: String?) { }" in content:
        content = content.replace('actual fun saveLastPlayerVideoId(videoId: String?) { }', 'actual fun saveLastPlayerVideoId(videoId: String?) { if (videoId != null) preferences.put("lastVideo", videoId) else preferences.remove("lastVideo") }')
        changed = True

    # PlayerSettingsStorage strings
    content, n = re.subn(r'actual fun save([A-Za-z0-9_]+)\(([A-Za-z0-9_]+):\s*String\?\)\s*\{\s*\}',
                         r'actual fun save\1(\2: String?) { if (\2 != null) preferences.put("\1", \2) else preferences.remove("\1") }',
                         content)
    if n > 0: changed = True

    # PlayerSettingsStorage Set<String>
    content, n = re.subn(r'actual fun load([A-Za-z0-9_]+)\(\):\s*Set<String>\?\s*=\s*null',
                         r'actual fun load\1(): Set<String>? = preferences.get("\1", null)?.split(",")?.toSet()',
                         content)
    if n > 0: changed = True
    content, n = re.subn(r'actual fun save([A-Za-z0-9_]+)\(([A-Za-z0-9_]+):\s*Set<String>\)\s*\{\s*\}',
                         r'actual fun save\1(\2: Set<String>) { preferences.put("\1", \2.joinToString(",")) }',
                         content)
    if n > 0: changed = True

    if changed:
        with open(filepath, 'w', encoding='utf-8') as f:
            f.write(content)

for root, dirs, files in os.walk(base_dir):
    for file in files:
        if file.endswith(".desktop.kt"):
            process_file(os.path.join(root, file))
