from ST7735 import TFT
from sysfont import sysfont
from machine import SoftSPI,Pin
import time
import math
import network
import time
import socket
import ntptime as ntp

spi=SoftSPI(baudrate=20000000, polarity=0, phase=0, sck=Pin(18), mosi=Pin(23), miso=Pin(19))
tft=TFT(spi,22,21,19)

tft.initr()
tft.rgb(True)
tft.fill(TFT.BLACK)

width=128
height=160

def tftPrintLines(): 
    tft.line((0,0),(width, height), TFT.WHITE)
    tft.line((width,0),(0, height), TFT.WHITE)
    
def tftPrintAlert():
    v = 120
    tft.text((40, v), "Alarm!", TFT.RED, sysfont, 1.5, nowrap=True)
    
def tftPrintComeAndGo():
    v = 70
    tft.text((5, v), "Kommen", TFT.GREEN, sysfont, 1.5, nowrap=True)
    
    v = 70
    tft.text((80, v), "Gehen", TFT.GREEN, sysfont, 1.5, nowrap=True)

def tftPrintDateAndTime():
    ssid = "SMARTments Ludwig Bamberger"
    key  = "ckmuKNW7LcNc"

    station = network.WLAN(network.STA_IF)
    station.active(True)
    print ("Connecting .",end="")

    while not station.isconnected():
        print (".",end="")
        station.connect(ssid, key)
        time.sleep(1)

    print(" Connected!")
    print("My IP Address:", station.ifconfig()[0])

    ntp.settime()
    UTC_OFFSET = 1 * 60 * 60
    actual_time = time.localtime(time.time() + UTC_OFFSET)
    
    v = 10
    tft.text((35, v), str(actual_time[2]) + "." + str(actual_time[1]) + "." + str(actual_time[0]), TFT.CYAN, sysfont, 1.3, nowrap=True)
    v += sysfont["Height"] * 2
    tft.text((45, v), str(actual_time[3]) + ":" + str(actual_time[4]), TFT.CYAN, sysfont, 1.5, nowrap=True)
    v += sysfont["Height"] * 2
    
    print(actual_time)

    s = socket.socket()
    ai = socket.getaddrinfo("pumpkin.international", 80)
    addr = ai[0][-1]
    s.connect(addr)
    s.send(b"GET / HTTP/1.0\r\n\r\n")
    print(s.recv(4096))
    s.close()

def test_main():
    tft.fill(TFT.BLACK)
    tftPrintLines()
    tftPrintAlert()
    tftPrintComeAndGo()
    tftPrintDateAndTime()

test_main()