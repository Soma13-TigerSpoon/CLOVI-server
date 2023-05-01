package com.clovi.app.timeframe;

import com.clovi.app.base.dto.response.BaseResponse;
import com.clovi.app.base.dto.response.MessageCode;
import com.clovi.app.base.dto.response.ProcessStatus;
import com.clovi.app.base.dto.response.SavedId;
import com.clovi.app.timeShopItem.dto.response.TimeShopItemResponse;
import com.clovi.app.timeframe.dto.request.TimeframeCreateRequest;
import com.clovi.app.timeframe.dto.request.TimeframeUpdateRequest;
import com.clovi.app.timeframe.dto.response.TimeframeResponse;
import com.clovi.app.auth.helper.AuthMember;
import com.clovi.app.member.Member;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(name = "[Timeframe] 아이템 등장 시간 관리 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TimeframeController {
    private final TimeframeService timeFrameService;

    // 영상에 대한 모든 시간 조회
    @GetMapping("/videos/{youtube_video_id}/timeframes")
    @Operation(summary = "Find all timeFrame", description = "Find all timeFrame by video ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Success Find timeFrame List", content = @Content(schema = @Schema(implementation = TimeframeResponse.class)))
    })
    public ResponseEntity findAllTimeFramesByVideoId(@PathVariable(name = "youtube_video_id") String youtubeVideoId){
        List<TimeframeResponse> response = timeFrameService.getTimeFrameListByYoutubeVideoId(youtubeVideoId);
        return ResponseEntity.ok(new BaseResponse(response, HttpStatus.OK.value(), MessageCode.SUCCESS_GET_LIST));
    }

    // 시간으로 해당 시간에 있는 아이템 리스트 조회 API
    @GetMapping("/videos/{youtube_video_id}/timeframes/{time_frame_id}")
    @Operation(summary = "Find timeFrame", description = "Find timeFrame by ID.", responses = {
            @ApiResponse(responseCode = "200", description = "Success Find timeFrame", content = @Content(schema = @Schema(implementation = TimeShopItemResponse.class)))
    })
    public ResponseEntity findItemsByTimeFrameId(@PathVariable(name = "youtube_video_id") String youtubeVideoId, @PathVariable(name = "time_frame_id") Long timeFrameId){
        TimeShopItemResponse response = timeFrameService.getItemListByTimeFrameId(timeFrameId);
        return ResponseEntity.ok(new BaseResponse(response, HttpStatus.OK.value(), MessageCode.SUCCESS_GET));
    }

    // 시간 생성 API
    @PostMapping("/videos/{youtube_video_id}/timeframes")
    @Operation(summary = "Create timeFrame", description = "Create timeFrame and save", responses = {
            @ApiResponse(responseCode = "201", description = "Success create", content = @Content(schema = @Schema(implementation = SavedId.class)))
    })
    public ResponseEntity createTimeFrame(@PathVariable(name = "youtube_video_id") String youtubeVideoId, @Validated @RequestBody TimeframeCreateRequest timeFrameCreateRequest, @AuthMember Member member){
        SavedId savedId = new SavedId(timeFrameService.create(timeFrameCreateRequest,youtubeVideoId,member));
        String[] list = {"/api/v1/videos", youtubeVideoId, "timeframes", String.valueOf(savedId.getId())};
        return ResponseEntity.created(
                URI.create(String.join("/", list))).body(new BaseResponse(savedId, HttpStatus.CREATED.value(),
                ProcessStatus.SUCCESS,
                MessageCode.SUCCESS_CREATE));
    }

    // 시간 수정 API
    @PutMapping("/videos/{youtube_video_id}/timeframes/{time_frame_id}")
    @Operation(summary = "Update timeFrame", description = "Update timeFrame by id", responses = {
            @ApiResponse(responseCode = "200", description = "Success update", content = @Content(schema = @Schema(implementation = SavedId.class)))
    })
    public ResponseEntity updateTimeFrame(@Validated @RequestBody TimeframeUpdateRequest timeFrameUpdateRequest, @PathVariable(name = "time_frame_id") Long timeFrameId, @AuthMember Member member, @PathVariable(name = "youtube_video_id") String youtubeVideoId){
        SavedId savedId = new SavedId(timeFrameService.update(timeFrameUpdateRequest,timeFrameId,member));
        return ResponseEntity.ok(new BaseResponse(savedId,HttpStatus.OK.value(),ProcessStatus.SUCCESS, MessageCode.SUCCESS_UPDATE));
    }

    // 시간 삭제 API
    @DeleteMapping("/videos/{youtube_video_id}/timeframes/{time_frame_id}")
    @Operation(summary = "Delete timeFrame", description = "Delete timeFrame by id", responses = {
            @ApiResponse(responseCode = "200", description = "Success delete")
    })
    public ResponseEntity deleteTimeFrame(@Validated @PathVariable(name = "time_frame_id") Long timeFrameId, @AuthMember Member member, @PathVariable(name = "youtube_video_id") String youtubeVideoId){
        timeFrameService.delete(timeFrameId,member);
        return ResponseEntity.ok(new BaseResponse(HttpStatus.OK.value(),ProcessStatus.SUCCESS, MessageCode.SUCCESS_DELETE));
    }
}