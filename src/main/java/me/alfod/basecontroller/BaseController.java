package me.alfod.basecontroller;

import com.gaosi.api.common.constants.ApiRetCode;
import com.gaosi.api.common.to.ApiResponse;
import com.gaosi.api.davinciNew.service.UserService;
import com.gaosi.api.davincicode.ManageService;
import com.gaosi.api.davincicode.common.service.UserSessionHandler;
import com.gaosi.api.davincicode.model.Manage;
import com.gaosi.api.davincicode.model.bo.UserBo;
import com.gaosi.api.matrix.model.bo.BaseBo.OperatorName;
import com.gaosi.api.matrix.model.po.template.OperatorTemplate;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Yang Dong
 * @createTime 2017/6/17  14:43
 * @lastUpdater Yang Dong
 * @lastUpdateTime 2017/6/17  14:43
 * @note contains basic controller tools
 */
public abstract class BaseController<BO> {
    private static Logger logger = LoggerFactory.getLogger(BaseController.class);
    @Resource
    private ManageService manageService;
    @Resource
    private UserService userServiceNew;


    protected static void apiResponseCheck(ApiResponse apiResponse) {
        Assert.notNull(apiResponse, "apiResponse is null.");
        Assert.isTrue(apiResponse.getRetCode() == ApiRetCode.SUCCESS_CODE, apiResponse.getMessage());
    }

    //functional function,for chaining invocation
    protected static <B extends OperatorTemplate> List<B> assignOperatorId(List<B> boList) {
        Integer operatorId = getOperatorId();

        if (operatorId == null) {
            operatorId = 1;
        }
        for (B b : boList) {
            b.setOperatorId(operatorId);
        }
        return boList;
    }

    protected static Integer getOperatorId() {
        Integer id = UserSessionHandler.getId();
        if (id == null) {
            id = 0;
        }
        return id;
    }

    //functional function,for chaining invocation
    protected <B extends OperatorTemplate> B assignOperatorId(B bo) {
        bo.setOperatorId(getOperatorId());
        return bo;
    }


    @SuppressWarnings("unchecked")
    protected Object assignOperatorName(Object operatorName) {
        if (operatorName == null) {
            return null;
        }
        if (operatorName instanceof OperatorName) {
            UserBo userBo = userServiceNew.findById(((OperatorName) operatorName).getOperatorId()).getBody();
            if (userBo != null) {
                ((OperatorName) operatorName).setOperatorName(userBo.getName());
            }
        }

        if (operatorName instanceof com.gaosi.api.common.template.OperatorName) {
            UserBo userBo =  userServiceNew.findById((((com.gaosi.api.common.template.OperatorName) operatorName).getOperatorId())).getBody();
            if (userBo != null) {
                ((com.gaosi.api.common.template.OperatorName) operatorName).setOperatorName(userBo.getName());
            }
        }
        return operatorName;
    }


    @SuppressWarnings("unchecked")
    protected List assignOperatorNameList(List operatorNameList) {
        if (CollectionUtils.isEmpty(operatorNameList)) {
            return operatorNameList;
        }
        for (Object operatorName : operatorNameList) {
            if (operatorName == null) {
                    continue;
                }
            if (operatorName instanceof OperatorName) {
                    return assignOperatorNameListMatrix(operatorNameList);
                }
            if (operatorName instanceof com.gaosi.api.common.template.OperatorName) {
                    return assignOperatorNameListNew(operatorNameList);
                }
        }
        return operatorNameList;
    }

    //assign operator name for list
    private List<com.gaosi.api.common.template.OperatorName> assignOperatorNameListNew(List<com.gaosi.api.common.template.OperatorName> operatorNameList) {
        if (!CollectionUtils.isEmpty(operatorNameList)) {
            List<Integer> userIds = Lists.newLinkedList();
            for (com.gaosi.api.common.template.OperatorName operatorName : operatorNameList) {
                userIds.add(operatorName.getOperatorId());
            }
            Map<Integer, UserBo> manageMap = getUserMapByManagerIds(userIds);
            UserBo userBo;
            for (com.gaosi.api.common.template.OperatorName operatorName : operatorNameList) {
                userBo = manageMap.get(operatorName.getOperatorId());
                if (userBo != null) {
                    operatorName.setOperatorName(userBo.getName());
                }
            }

        }
        return operatorNameList;
    }

    //assign operator name for list  in matrix
    private List<OperatorName> assignOperatorNameListMatrix(List<OperatorName> operatorNameList) {
        if (!CollectionUtils.isEmpty(operatorNameList)) {
            List<Integer> userIds = Lists.newLinkedList();
            for (OperatorName operatorName : operatorNameList) {
                userIds.add(operatorName.getOperatorId());
            }
            Map<Integer, UserBo> userBoMap = getUserMapByManagerIds(userIds);
            UserBo userBo;
            for (OperatorName operatorName : operatorNameList) {
                userBo = userBoMap.get(operatorName.getOperatorId());
                if (userBo != null) {
                    operatorName.setOperatorName(userBo.getName());
                }
            }

        }
        return operatorNameList;
    }

    /**
     * 根据userIds查询获取userId-Manage的Map
     *
     * @param userIds userIds
     * @return userId和Manage的映射Map
     */
    protected Map<Integer, Manage> getManageMapByManagerIds(Collection<Integer> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return Maps.newHashMap();
        }
        Map<Integer, Manage> manageMap = Maps.newHashMap();
        List<Manage> managerList = manageService.getByIds(userIds);
        if (CollectionUtils.isEmpty(managerList)) {
            return Maps.newHashMap();
        }
        for (Manage manage : managerList) {
            if (manage != null) {
                manageMap.put(manage.getId(), manage);
            }
        }
        return manageMap;
    }

    protected Map<Integer, UserBo> getUserMapByManagerIds(Collection<Integer> userIds) {
        if (CollectionUtils.isEmpty(userIds)) {
            return Maps.newHashMap();
        }
        Map<Integer, UserBo> userMap = Maps.newHashMap();
        List<UserBo> userBoList = userServiceNew.findByIdsWithoutRolename(Lists.newArrayList(userIds)).getBody();
        if (CollectionUtils.isEmpty(userBoList)) {
            return Maps.newHashMap();
        }
        for (UserBo userBo : userBoList) {
            if (userBo != null) {
                userMap.put(userBo.getId(), userBo);
            }
        }
        return userMap;
    }

    protected ResultBody checkThenReturn(ApiResponse apiResponse) {
        if (apiResponse == null) {
            return ResultBody.failed("操作执行失败");
        }
        if (apiResponse.isNotSuccess()) {
            return ResultBody.failed(apiResponse.getMessage(),apiResponse.getBody());
        }
        return ResultBody.successed(apiResponse.getBody());
    }

    protected com.aixuexi.thor.response.ResultBody checkThenReturn(com.aixuexi.thor.response.ApiResponse apiResponse) {
        if (apiResponse == null) {
            return com.aixuexi.thor.response.ResultBody.failed("操作执行失败");
        }
        if (apiResponse.isNotSuccess()) {
            return com.aixuexi.thor.response.ResultBody.failed(apiResponse.getMessage(),apiResponse.getBody());
        }
        return com.aixuexi.thor.response.ResultBody.successed(apiResponse.getBody());
    }

    /**
     * 校验ApiResponse是否不为空
     *
     * @param apiResponse
     * @return
     */
    protected boolean isNotEmpty(ApiResponse apiResponse) {
        return !isEmpty(apiResponse);
    }

    /**
     * 校验ApiResponse是否为空
     *
     * @param apiResponse
     * @return
     */
    protected boolean isEmpty(ApiResponse apiResponse) {
        if (apiResponse == null || apiResponse.isNotSuccess() || apiResponse.getBody() == null) {
            return true;
        }
        return false;
    }
}
