import time, sys

def output():
    sys.stdout.write("{Objects:[{classe:'visconti', prob:'100',bx:'0.448186', by:'0.353241', bw:'0.174904', bh:'0.417365'},{classe:'ades', prob:'98',bx:'0.640000', by:'0.302042', bw:'0.152708', bh:'0.553296'},{classe:'ades', prob:'98',bx:'0.640000', by:'0.302042', bw:'0.152708', bh:'0.553296'},{classe:'ades', prob:'98',bx:'0.640000', by:'0.302042', bw:'0.152708', bh:'0.553296'},{classe:'italac', prob:'96',bx:'0.303978', by:'0.412921', bw:'0.121380', bh:'0.306080'}]}")
    sys.stdout.flush()

while True:
    time.sleep(3)
    output()