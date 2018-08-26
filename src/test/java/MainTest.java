import com.aliyuncs.vod.model.v20170321.DeleteVideoResponse;
import com.aliyuncs.vod.model.v20170321.UpdateVideoInfoResponse;
import com.yosoro.video.config.MyBatisConfig;
import com.yosoro.video.dao.UserMapper;
import com.yosoro.video.dao.VideoMapper;
import com.yosoro.video.domain.video.TypeList;
import com.yosoro.video.domain.video.Video;
import com.yosoro.video.domain.video.VideoType;
import com.yosoro.video.oss.OssInstance;
import com.yosoro.video.service.VideoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyBatisConfig.class, VideoService.class})
public class MainTest {
    @Autowired(required = false)
    private VideoService videoService;
    @Autowired(required = false)
    private VideoMapper videoMapper;
    @Autowired(required = false)
    private UserMapper userMapper;
    @Test
    public void test(){
        videoService.addNewVideoType("页面设计",590519275);
        videoService.addNewVideoType("PS从入门到放弃",590519275);
    }
}
