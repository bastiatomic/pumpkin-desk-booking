from ST7735 import TFT
from sysfont import sysfont
from machine import SoftSPI,Pin
from machine import Pin
from neopixel import NeoPixel


def tftPrintLines(tft):
    global width, height
    tft.line((0,0),(width, height), TFT.WHITE)
    tft.line((width,0),(0, height), TFT.WHITE)
    
def tftPrintAlert(tft):
    v = 120
    tft.text((40, v), "Alarm!", TFT.RED, sysfont, 1.5, nowrap=True)
    
def tftPrintCome():
    print("tftPrintCome")
    spi=SoftSPI(baudrate=20000000, polarity=0, phase=0, sck=Pin(18), mosi=Pin(23), miso=Pin(19))
    tft=TFT(spi,22,21,19)
    v = 70
    tft.text((80, v), "Gehen", TFT.BLACK, sysfont, 1.5, nowrap=True)
    tft.text((5, v), "Kommen", TFT.GREEN, sysfont, 1.5, nowrap=True)
    pixels=12
    pin = Pin(14, Pin.OUT)   
    np = NeoPixel(pin, pixels) 
    for i in range(0,12,1):
        np[i] = (0, 32, 0)
    np.write()      

def tftPrintGo():
    print("tftPrintGo")
    spi=SoftSPI(baudrate=20000000, polarity=0, phase=0, sck=Pin(18), mosi=Pin(23), miso=Pin(19))
    tft=TFT(spi,22,21,19)
    v = 70
    tft.text((5, v), "Kommen" , TFT.BLACK, sysfont, 1.5, nowrap=True)
    tft.text((80, v),"Gehen", TFT.GREEN, sysfont, 1.5, nowrap=True)
    pixels=12
    pin = Pin(14, Pin.OUT)   
    np = NeoPixel(pin, pixels) 
    for i in range(0,12,1):
        np[i] = (32, 0, 0)
    np.write()   
    
def tftPrintDateAndTime(actual_time,actual_time_old):
    print("tftPrintDateAndTime()")
    print(actual_time)
    global width, height
    spi=SoftSPI(baudrate=20000000, polarity=0, phase=0, sck=Pin(18), mosi=Pin(23), miso=Pin(19))
    tft=TFT(spi,22,21,19)
    
   
  
    v = 10
   
  
    tft.text((35, v), str(actual_time[2]) + "." + str(actual_time[1]) + "." + str(actual_time[0]), TFT.CYAN, sysfont, 1.3, nowrap=True)
    v += sysfont["Height"] * 2
    
    ts="{HH}:{MM:0>2d}".format(HH=actual_time[3],MM=actual_time[4])
    tso="{HH}:{MM:0>2d}".format(HH=actual_time_old[3],MM=actual_time_old[4])
    #print (tso)
    #print (ts)
    
    tft.text((45, v), tso, TFT.BLACK, sysfont, 1.5, nowrap=True)
    tft.text((45, v),ts, TFT.CYAN, sysfont, 1.5, nowrap=True)
    
    
  
def main():
    print("main()")
    global width, height
    spi=SoftSPI(baudrate=20000000, polarity=0, phase=0, sck=Pin(18), mosi=Pin(23), miso=Pin(19))
    tft=TFT(spi,22,21,19)
    tft.initr()
    tft.rgb(True)
    tft.fill(TFT.BLACK)
    width=128
    height=160
    tftPrintLines(tft)
    tftPrintAlert(tft)
    #tftPrintGo()
    #tftPrintCome()
   

