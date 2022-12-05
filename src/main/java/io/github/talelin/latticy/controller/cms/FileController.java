package io.github.talelin.latticy.controller.cms;

import io.github.talelin.core.annotation.LoginRequired;
import io.github.talelin.latticy.bo.FileBO;
import io.github.talelin.latticy.model.InsSendUserInfoDO;
import io.github.talelin.latticy.service.FileService;
import io.github.talelin.latticy.service.InsSendUserInfoService;
import io.github.talelin.latticy.vo.OperateVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * 文件控制器
 * @author pedro@TaleLin
 * @author Juzi@TaleLin
 */
@RestController
@RequestMapping("/cms/file")
public class FileController {

    @Autowired
    private InsSendUserInfoService sendUserService;

    @Autowired
    private FileService fileService;

    /**
     * 文件上传
     *
     * @param multipartHttpServletRequest 携带文件的 request
     * @return 文件信息
     */
    @PostMapping
    @LoginRequired
    public List<FileBO> upload(MultipartHttpServletRequest multipartHttpServletRequest) {
        MultiValueMap<String, MultipartFile> fileMap =
                multipartHttpServletRequest.getMultiFileMap();
        return fileService.upload(fileMap);
    }

    @PostMapping("/text")
    @LoginRequired
    public OperateVO uploadText(MultipartHttpServletRequest multipartHttpServletRequest) throws IOException {
        MultiValueMap<String, MultipartFile> fileMap =
                multipartHttpServletRequest.getMultiFileMap();
        List<MultipartFile> file = fileMap.get("file");
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(file.get(0).getInputStream()));
        String lineTxt;
        InsSendUserInfoDO insSendUserInfo = new InsSendUserInfoDO();
        int count = 0;
        int error = 0;
        while ((lineTxt=bufferedReader.readLine())!=null){
            if(sendUserService.isExistReceive(lineTxt.trim())){
                error++;
                continue;
            }
            insSendUserInfo.setUsername(lineTxt.trim());
            sendUserService.createReceive(insSendUserInfo);
            count++;
        }
        if(count == 0 && error == 0){
            return new OperateVO(10400,"该文件内容为空,请重新上传其它文件");
        }
        bufferedReader.close();
        return new OperateVO(31,"已导入成功"+count+"条数据.导入失败"+error+"条数据");
    }
}
