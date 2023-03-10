import time, ds18x20, machine, onewire
ow = onewire.OneWire(machine.Pin(32))
ds = ds18x20.DS18X20(ow)
roms = ds.scan()
ds.convert_temp()
time.sleep_ms(750)
for rom in roms:
    print(ds.read_temp(rom))