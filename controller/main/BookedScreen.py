from ST7735 import TFT
from sysfont import sysfont
from machine import SoftSPI,Pin
import time
import math

spi=SoftSPI(baudrate=20000000, polarity=0, phase=0, sck=Pin(18), mosi=Pin(23), miso=Pin(19))
tft=TFT(spi,22,21,19)

tft.initr()
tft.rgb(True)
tft.fill(TFT.BLACK)

width=128
height=160

def tftPrintColor():
    for i in range(1,20,1):
        tft.hline((i,i), width-(2*i), TFT.BLUE )

    for i in range(1,20,1):
        tft.vline((i,i), height-(2*i), TFT.YELLOW )

    for i in range(1,20,1):
        tft.vline((width-i-1,i), height-(2*i), TFT.GREEN )

    for i in range(1,20,1):
        tft.hline((i,height-i), width-(2*i), TFT.RED )
    
def tftPrintText():    
    v = 45
    tft.text((25, v), "RESERVIERT", TFT.RED, sysfont, 1.35, nowrap=True)
    v += sysfont["Height"] * 2
    tft.text((25, v), "VON:", TFT.RED, sysfont, 1.35, nowrap=True)
    v += sysfont["Height"] * 4
    tft.text((25, v), "VORNAME", TFT.CYAN, sysfont, 1.35, nowrap=True)
    v += sysfont["Height"] * 2
    tft.text((25, v), "NACHNAME", TFT.CYAN, sysfont, 1.35, nowrap=True)
    v += sysfont["Height"] * 2

def main():
    tft.fill(TFT.BLACK)
    tftPrintColor()
    tftPrintText()

