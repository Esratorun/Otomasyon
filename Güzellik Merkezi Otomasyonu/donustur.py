# -*- coding: utf-8 -*-
"""
Spyder Editor

This is a temporary script file.
"""

from PyQt5 import uic

with open("GoldenAuraUi.py","w",encoding="utf-8")as fout:
    uic.compileUi("GoldenAura.ui",fout)