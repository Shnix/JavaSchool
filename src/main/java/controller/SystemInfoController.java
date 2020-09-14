package controller;


import dto.SystemInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.SystemInfoService;

@RestController
@CrossOrigin
@RequestMapping("/info")
public class SystemInfoController {

    private SystemInfoService systemInfoService;

    @Autowired
    public SystemInfoController(SystemInfoService systemInfoService) {
        this.systemInfoService = systemInfoService;
    }

    @GetMapping("")
    public SystemInfoDto getInfo() {
        return systemInfoService.getSystemStatistics();
    }
}
