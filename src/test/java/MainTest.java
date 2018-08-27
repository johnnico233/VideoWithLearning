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
        List<Video> list=videoMapper.getVideoListByTypeId(47937346);
        for(Video video:list)
            System.out.println(video);
    }
}
