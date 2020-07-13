package com.zyl.testsystem.util;

import com.zyl.testsystem.common.AppConstat;
import com.zyl.testsystem.vo.GroupListVO;
import com.zyl.testsystem.vo.GroupVo;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author jiangtingfeng
 * @description 分组
 * @date 2020/6/16/016
 */

public class DepartGroup {

    /**
     * @Description:
     * 第一组：学习阶段呈现750张原图，测试阶段也呈现的是原图
     * 第二组：学习阶段呈现750张原图，测试阶段呈现的是200张前景图。
     * 第三组：学习阶段呈现750张原图，测试阶段呈现的是200张背景图。
     * 第四组：学习阶段呈现750张背景图，测试阶段呈现的是200张背景图。
     * 第五组：学习阶段呈现750张前景图，测试阶段呈现的是200张前景图。
     * @Author: jiangtingfeng
     */
    public static GroupListVO getGroup(String groupName) {
        GroupListVO build = null;
        //悬着相应的路径
        if (groupName.equals("第一组")) {
            //原图  随机打乱 获得相应的750张图片索引 第一组：学习阶段呈现750张原图，测试阶段也呈现的是原图
            build = buildFirst(AppConstat.LOCAL_SOURCE_DIR);
        } else if (groupName.equals("第二组")) {
            // 第二组：学习阶段呈现750张原图，测试阶段呈现的是200张前景图。
            build = buildFirst(AppConstat.LOCAL_SOURCE_DIR);
            //改变测试阶段图片的路径
            changeImageUrl(AppConstat.LOCAL_SOURCE_DIR,build,AppConstat.LOCAL_OBJ_DIR);
        } else if (groupName.equals("第三组")) {
            build = buildFirst(AppConstat.LOCAL_SOURCE_DIR);
            changeImageUrl(AppConstat.LOCAL_SOURCE_DIR,build,AppConstat.LOCAL_BACKIMAGE_DIR);
        } else if (groupName.equals("第四组")) {
            build = buildFirst(AppConstat.LOCAL_BACKIMAGE_DIR);
        } else {
            build = buildFirst(AppConstat.LOCAL_OBJ_DIR);
        }
        return build;
    }

    private static void changeImageUrl(String source,GroupListVO groupListVO, String target) {
        List<String> imageTestList = groupListVO.getImageTestList();
        for (String imageTest:imageTestList) {
            imageTest.replace(target,source);
        }
    }


    private static GroupListVO buildFirst(String localSourceDir) {
        GroupListVO groupListVO = new GroupListVO();
        HashMap<Integer, String> map = new HashMap<>();
        for (int i=1; i<= 850;i++) {
            map.put(i,i+AppConstat.PIC_SUFFIX);
        }
        //获取学习阶段图
        GroupVo groupVo = getOrginalImage(map, localSourceDir);
        List<String> imagePathList = groupVo.getImagePathList();
        Collections.shuffle(imagePathList);
        groupListVO.setImageLearnList(imagePathList);
        groupListVO.setImageLearnNameList(groupVo.getImageNameList());
        //获取测试用例图
        GroupVo groupVoTest = getTestImages(map, groupVo, localSourceDir);
        Collections.shuffle(groupVoTest.getImagePathList());
        groupListVO.setImageTestList(groupVoTest.getImagePathList());
        groupListVO.setImageTestNameList(groupVoTest.getImageNameList());
        return groupListVO;
    }

    /**
     * @Description:  获取750张原图测试图方法
     * @Author: jiangtingfeng
     */
    public static GroupVo getOrginalImage(HashMap<Integer,String> map,String localSourceDir){
        List<String> imageList = new ArrayList<>(750);
        List<String> imageNameList = new ArrayList<>(750);
        HashSet<Integer> set = new HashSet<>();
        //随机获取750张图片作为测试图片
        while (set.size() < 750) {
            set.add(new Random().nextInt(850)+1);
        }
        Iterator<Integer> iterator = set.iterator();
        while (iterator.hasNext()) {
            Integer next = iterator.next();
            String remove = map.remove(next);
            String imagePath =   "/" + localSourceDir +  "/" + remove;
            imageList.add(imagePath);
            imageNameList.add(remove);
        }
        Collections.shuffle(imageList);
        GroupVo groupVo = new GroupVo();
        groupVo.setImagePathList(imageList);
        groupVo.setImageNameList(imageNameList);
        return groupVo;
    }

    /**
     * @Description: 获取测试阶段用例图
     * @Author: jiangtingfeng
     */
    public static GroupVo getTestImages(HashMap<Integer,String> map,GroupVo groupVo,String localSourceDir) {
        Set<Integer> integers = map.keySet();
        Iterator<Integer> iteratorLeft = integers.iterator();
        ArrayList<String> arrayList = new ArrayList<>();
        ArrayList<String> arrayNameList = new ArrayList<>();
        GroupVo groupVoTest = new GroupVo();
        while (iteratorLeft.hasNext()) {
            Integer next = iteratorLeft.next();
            arrayNameList.add(map.get(next));
            arrayList.add("/"+localSourceDir+"/"+map.get(next));
        }
        for (int j=0; j<100; j++) {
            arrayList.add(groupVo.getImagePathList().get(new Random().nextInt(750)));
            arrayNameList.add(groupVo.getImageNameList().get(new Random().nextInt(750)));
        }
        groupVoTest.setImagePathList(arrayList);
        groupVoTest.setImageNameList(arrayNameList);
        //还需要加上一些风格不一样的图
        addOtherImages(groupVoTest);
        Collections.shuffle(groupVoTest.getImagePathList());
        return groupVoTest;
    }

    private static void  addOtherImages(GroupVo groupVoTest) {
        for (int i=AppConstat.LOCAL_FAKE_START;i<=AppConstat.LOCAL_FAKE_END;i++){
            groupVoTest.getImagePathList().add("/"+AppConstat.LOCAL_FAKE_DIR+"/"+i+AppConstat.PIC_SUFFIX);
            groupVoTest.getImageNameList().add(i+AppConstat.PIC_SUFFIX);
        }
        Collections.shuffle(groupVoTest.getImagePathList());
    }
}
