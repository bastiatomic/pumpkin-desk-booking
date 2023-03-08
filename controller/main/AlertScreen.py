from ST7735 import TFT
from sysfont import sysfont
from machine import SoftSPI,Pin
import time
import math

def tftprinttest(tft):
    tft.fill(TFT.BLACK);
    v = 30
    tft.text((0, v), "FEUER!", TFT.RED, sysfont, 4, nowrap=True)
    v += sysfont["Height"] * 5
    tft.text((0, v), "ALARM!", TFT.RED, sysfont, 4, nowrap=True)
    v += sysfont["Height"] * 5
    time.sleep_ms(3500)
    tft.fill(TFT.BLACK);

    v2 = 10
    tft.text((0, v2), "  BITTE", TFT.RED, sysfont, 2, nowrap=True)
    v2 += sysfont["Height"] * 3
    tft.text((0, v2), "  VERLASSEN", TFT.RED, sysfont, 2, nowrap=True)
    v2 += sysfont["Height"] * 3
    tft.text((0, v2), "  SIE", TFT.RED, sysfont, 2, nowrap=True)
    v2 += sysfont["Height"] * 3
    tft.text((0, v2), "  SOFORT", TFT.RED, sysfont, 2, nowrap=True)
    v2 += sysfont["Height"] * 3
    tft.text((0, v2), "  IHREN", TFT.RED, sysfont, 2, nowrap=True)
    v2 += sysfont["Height"] * 3
    tft.text((0, v2), "  PlATZ !!!", TFT.RED, sysfont, 2, nowrap=True)
    v2 += sysfont["Height"] * 3

def main():
    spi = SoftSPI(baudrate=20000000, polarity=0, phase=0, sck=Pin(18), mosi=Pin(23), miso=Pin(19))
    tft=TFT(spi,22,21,19)
    tft.fill(TFT.BLACK)
    tftprinttest(tft)