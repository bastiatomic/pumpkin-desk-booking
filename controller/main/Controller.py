import network
import time
import socket
import ntptime as ntp
import settings
import sys
import DefaultScreen
import BookedScreen
import machine
import urequests
import AlertScreen
# Vars
actual_time_old=time.localtime()
old_ticks=time.ticks_ms()

password=""
i=0

#status="booked" # todo: REST Call
status="default" # todo: REST Call
# statiu abfragen über
# http://pumpkin.international:8080/api/desk/status?deskID=21 true false
# Setup Joystick
xCoords = machine.ADC(machine.Pin(33))
xCoords.atten(machine.ADC.ATTN_11DB)
yCoords = machine.ADC(machine.Pin(34))
yCoords.atten(machine.ADC.ATTN_11DB)
trigger = machine.Pin(25,machine.Pin.IN, machine.Pin.PULL_UP)

print (settings.ssid)
print (settings.DeskID)

while True:
    try:
        # WLAN Connection
        station = network.WLAN(network.STA_IF)
        station.active(True)
        print ("Connecting .",end="")
        while not station.isconnected():
            print (".",end="")
            station.connect(settings.ssid, settings.key)
            time.sleep(1)
        print(" Connected!")
        print("My IP Address:", station.ifconfig()[0])
        ntp.settime()
        UTC_OFFSET = 1 * 60 * 60
        # Mainloop
        firstrun=True
        while True:
            
            actual_time = time.localtime(time.time() + UTC_OFFSET)
            time.sleep(0.5)
            if((status=="booked")):
                print("booked")
                status="bookedactive"
                BookedScreen.main()
                
            # REST Call
            if((status=="default")):
                #call default Screen
                DefaultScreen.main()
                status="defaultactive"
            
            if((status=="defaultactive")and (yCoords.read() > 4000)and (lock==False)):
                print("go")
                lock=True
                url="http://pumpkin.international:8080/api/desk/free?deskID="+str(settings.DeskID)
                print (url)
                response = urequests.get(url)
                response.close()
                firstrun=True
            
            if((status=="defaultactive")and (yCoords.read() < 100 )and (lock==False)):
                print("Come")
                lock=True
                url="http://pumpkin.international:8080/api/desk/block?deskID="+str(settings.DeskID)
                print (url)
                response = urequests.get(url)
                response.close()
                firstrun=True
            # Nach der Pause booking mit einbauen
            # http://pumpkin.international:8080/api/booking?deskID=1
            if(((time.ticks_ms() > (old_ticks))) or firstrun):
                old_ticks=time.ticks_ms()+settings.REST_CALL_INTERVALL
                print("REST Call")
                url_status="http://pumpkin.international:8080/api/desk/status?deskID="+str(settings.DeskID)
                url_booking="http://pumpkin.international:8080/api/booking?deskID="+str(settings.DeskID)
                print("Call Status: "+url_status)
                print("Call Booking: "+url_booking)
                response_status = urequests.get(url_status)
                response_booking = urequests.get(url_booking)
                json_status=response_status.json()
                json_booking=response_booking.json()
                print("hier bin ich "+str(json_booking["booked"]))
                
               
                if((json_booking["booked"]==True) and not(status=="bookedactive")):
                    status="booked"
                if((json_booking["booked"]==False) and not(status=="defaultactive")):
                    DefaultScreen.main()
                    DefaultScreen.tftPrintDateAndTime(actual_time,actual_time_old)
                    status="defaultactive"
                if((json_status["blocked"]==True) and (json_booking["booked"]==False)):
                    status="defaultactive"
                    DefaultScreen.tftPrintGo()
                if((json_status["blocked"]==False) and (json_booking["booked"]==False)and not(status=="bookedactive")):
                    status="defaultactive"
                    DefaultScreen.tftPrintCome()
                
                response_status.close()
                response_booking.close()
                    
                
            # Uhrzeit
            if((actual_time[4] != actual_time_old[4]) or firstrun):
                if (status=="defaultactive"):
                    DefaultScreen.tftPrintDateAndTime(actual_time,actual_time_old)
                actual_time_old=actual_time
            
            # DefaultScreen 
            if((status=="defaultactive") and (xCoords.read() == 0) and (lock==False)):
                print("Alarm")
                lock=True
                AlertScreen.main()
                url="http://pumpkin.international:8080/api/desk/free?deskID="+str(settings.DeskID)
                print (url)
                #response = urequests.get(url)
                response.close()
                status="alertactive"
            
            
            # BookedScreen    
            if((status=="bookedactive1") and (xCoords.read() >= 0 and xCoords.read() < 100) and (yCoords.read() == 0 or xCoords.read() == 0) and (lock==False)):
                lock=True
                i+=1
                password+="0"
                print("red")
            if((status=="bookedactive1") and (xCoords.read() > 3900 and xCoords.read() <= 4095 and yCoords.read() > 0 and yCoords.read() < 4095) and (lock==False)):
                lock=True
                i+=1
                password+="1"
                print("blue")
            if((status=="bookedactive1") and (yCoords.read() > 3900 and yCoords.read() <= 4095 and xCoords.read() > 0) and (lock==False)):
                lock=True
                i+=1
                password+="2"
                print("green")
            if((status=="bookedactive1") and (yCoords.read() >= 0 and yCoords.read() < 100) and (lock==False)):
                lock=True
                i+=1
                password+="3"
                print("yellow")
                
            if((status=="bookedactive") and not(trigger.value())):
                print("Key pressed 1")
                status="bookedactive1"
                password=""
                i=0
                
            if((status=="bookedactive1") and not(trigger.value()) and i>=3):
                print("Key pressed 2")
                # REST
                status="bookedactive"
                #password=""
                #i=0
             
             
                
            if((status=="bookedactive" or status=="defaultactive" or status=="bookedactive1") and (1500<yCoords.read()<2500) and (1500<xCoords.read()<2500)):
                print ("Reset")
                lock=False
            print(status)
            #print(not(trigger.value()))
            firstrun=False    
        # Mainloop
    except KeyboardInterrupt:
        print ("interrupt")
        sys.exit(0)
    except Exception as e:
        print(e)
        time.sleep(10)
        pass
sys.exit(0)


# -Alarm
# -Screen Booking
# -Password input
# -REST Call