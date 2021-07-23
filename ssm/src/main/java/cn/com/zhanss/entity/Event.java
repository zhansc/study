package cn.com.zhanss.entity;

/**
 * Created by leduo on 17/4/17.
 */
public enum Event {

    LOCATION, MASSSENDJOBFINISH, subscribe, unsubscribe, SCAN, CLICK, VIEW, TEMPLATESENDJOBFINISH, WifiConnected, user_view_card, user_get_card, user_consume_card, user_scan_product_async, ShakearoundUserShake, card_pass_check, card_not_pass_check,
    kf_close_session, kf_create_session, pic_photo_or_album, scancode_waitmsg, user_scan_product, user_enter_session_from_card, user_gifting_card, submit_membercard_user_info, update_member_card, user_del_card, qualification_verify_success,
    location_select, merchant_order, user_pay_from_pay_cell, card_sku_remind, scancode_push, kf_switch_session, user_scan_product_verify_action, naming_verify_success, pic_sysphoto, card_merchant_check_result, shakearoundlotterybind,
    weapp_audit_success, weapp_audit_fail, weapp_audit_delay, open_product_spu_audit, qualification_verify_fail, naming_verify_fail, annual_renew, verify_expired, wx_verify_dispatch, wx_verify_refill,
    pic_weixin, poi_check_notify, user_scan_product_enter_session,
    user_enter_tempsession, user_authorize_invoice, wxa_widget_data, view_miniprogram,
    nearby_category_audit_info, add_nearby_poi_audit_info, add_express_path, change_contact, batch_job_result, change_external_contact, change_external_chat, enter_agent, change_external_tag,
    delete_schedule, modify_schedule, add_schedule, delete_calendar, modify_calendar, add_calendar, switch_workbench_mode, sys_approval_change, open_approval_change,
    wxa_category_audit, wxa_nickname_audit,
    open_product_brand_audit, open_product_category_audit,
    /**
     * 微信关注事件推送上云
     */
    subscribe_cloud
}
