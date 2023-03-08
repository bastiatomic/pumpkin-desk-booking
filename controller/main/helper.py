#helper
def readStick(xCoords,yCoords):
    global red, blue, yellow, green
    print (xCoords.read())
    print (yCoords.read())
    
    if (xCoords.read() >= 0 and xCoords.read() < 100) and (yCoords.read() == 0 or xCoords.read() == 0):
        red=True
    elif xCoords.read() > 3900 and xCoords.read() <= 4095 and yCoords.read() > 0 and yCoords.read() < 4095:
        blue=True
    elif yCoords.read() > 3900 and yCoords.read() <= 4095 and xCoords.read() > 0:
        green=True
    elif yCoords.read() >= 0 and yCoords.read() < 100:
        yellow=True
    else:
        yellow=False
        green=False
        blue=False
        red=False
    return (yellow,green,blue,red)
