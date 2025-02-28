# -*- coding: utf-8 -*-
"""
Created on Sun Jun  2 11:21:18 2024

@author: esrat
"""

from PyQt5 import uic

with open("HakkindaUi.py","w",encoding="utf-8")as fout:
    uic.compileUi("Hakkinda.ui",fout)