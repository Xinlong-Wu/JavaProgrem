package imgzip.mainwindow;

/**
 * @author 乌鑫龙
 *   压缩图片的线程
 */
class ZipImg implements Runnable{
    ImgBlock imgBlock;
    String sorce;
    double rate;
    public ZipImg(ImgBlock imgBlock, double rate){
        this.imgBlock = imgBlock;
        this.rate = rate;
        this.sorce = imgBlock.getUrl();
    }
    @Override
    public void run() {
        try {
            System.out.println("Ziping...");
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("done");
        imgBlock.getIvstate().setImage(ImgBlock.DONE);
    }


}
