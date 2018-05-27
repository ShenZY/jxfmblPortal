/**
     *
     * TODO:非递归方式扫描指定文件夹下面的所有文件
     * @return ArrayList<Object>
     * @param folderPath 需要进行文件扫描的文件夹路径
     * @author 邪恶小先生（LQ）
     * @time 2017年11月3日
     */
    @Test
    public void scanFilesWithNoRecursion() throws Exception{
        String folderPath = "D:\\Studio\\LiLi\\portal\\statics\\images\\jxfmbl\\";
        File directory = new File(folderPath);
        if(!directory.isDirectory()){
            throw new Exception('"' + folderPath + '"' + " input path is not a Directory , please input the right path of the Directory. ^_^...^_^");
        }
        else{
            //首先将第一层目录扫描一遍
            File [] files = directory.listFiles();
            //遍历扫出的文件数组，如果是文件夹，将其放入到linkedList中稍后处理
            for(int i = 0; i < files.length; i ++){
                if(files[i].isDirectory()){
                    queueFiles.add(files[i]);
                }else{
                    //暂时将文件名放入scanFiles中
                    String fileName = files[i].getName();
                    scanFiles.add(fileName.substring(0, fileName.length() - 4));
                }
            }
            String temp = "\t\t\t\t\t<a href=\"javascript:void(0);\" data-id=\"%s\">\n" + "\t\t\t\t\t\t<div class=\"pic\">\n"
                + "\t\t\t\t\t\t\t<img src=\"statics/images/jxfmbl/%s/%s.jpg\" alt=\"%s\" />\n"
                + "\t\t\t\t\t\t\t<div class=\"ad\">\n" + "\t\t\t\t\t\t\t\t%s </div>\n" + "\t\t\t\t\t\t</div>\n"
                + "\t\t\t\t\t\t<div class=\"name\">\n" + "\t\t\t\t\t\t\t%s<br />\n" + "\t\t\t\t\t\t</div>\n"
                + "\t\t\t\t\t</a>\n";
            StringBuilder sb = new StringBuilder();

            //如果linkedList非空遍历linkedList
            while(!queueFiles.isEmpty()){
                //移出linkedList中的第一个
                File headDirectory = queueFiles.removeFirst();
                File [] currentFiles = headDirectory.listFiles();
                for(int j = 0; j < currentFiles.length; j ++){
                    if(currentFiles[j].isDirectory()){
                        //如果仍然是文件夹，将其放入linkedList中
                        queueFiles.add(currentFiles[j]);
                    }else{
                        String fileName = currentFiles[j].getName();
                        String real = fileName.substring(0, fileName.length() - 4);
                        //scanFiles.add(fileName.substring(0, fileName.length() - 4));
                        sb.append(String.format(temp, j, headDirectory.getName(), real, real, real, real));
                    }
                }
            }
            String str = sb.toString();
        }
    }