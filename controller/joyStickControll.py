import machine, time

xCoords = machine.ADC(machine.Pin(33))
xCoords.atten(machine.ADC.ATTN_11DB)

yCoords = machine.ADC(machine.Pin(34))
yCoords.atten(machine.ADC.ATTN_11DB)

trigger = machine.ADC(machine.Pin(35))
trigger.atten(machine.ADC.ATTN_11DB)

i=0
while True:
    time.sleep(0.2)
    print(i, xCoords.read(), yCoords.read(), trigger.read())
    i += 1