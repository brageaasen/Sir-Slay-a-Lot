#!/usr/env/python3

# Extremely simple python script for creating spritesheets by combining all sprites in a folder.

import os

# aseprite executable location (change this)
ASEPRITE = "~/.var/app/com.valvesoftware.Steam/.local/share/Steam/steamapps/common/Aseprite/aseprite"

folder = input("folder:")

dir = os.listdir(folder)
for x in dir:
    # *nix shell command, probably won't work on windows (maybe in powershell?)
    os.system(f"{ASEPRITE} -b {folder}/{x}/{x}*.png --sheet {folder}/{x}.png")
