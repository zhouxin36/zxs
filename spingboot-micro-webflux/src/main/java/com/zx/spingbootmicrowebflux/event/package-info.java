/**
 * @author zhouxin
 * @EventListener public ListUpdateEvent handleBlackListEvent(BlackListEvent event) {
 *   // notify appropriate parties via notificationAddress and
 *   // then publish a ListUpdateEvent...
 * }
 * <p>
 * This feature is not supported for asynchronous listeners
 * <p>
 * This new method publishes a new ListUpdateEvent for every BlackListEvent handled by the method
 * above. If you need to publish several events, you can return a Collection of events instead.
 * @since 2019/2/28
 * <p>
 * 可用注解{@link org.springframework.context.event.EventListener}
 */
/**
 * 可用注解{@link org.springframework.context.event.EventListener}
 *
 *
 *
 * @EventListener
 * public ListUpdateEvent handleBlackListEvent(BlackListEvent event) {
 *   // notify appropriate parties via notificationAddress and
 *   // then publish a ListUpdateEvent...
 * }
 *
 * This feature is not supported for asynchronous listeners
 *
 * This new method publishes a new ListUpdateEvent for every BlackListEvent handled by the method
 * above. If you need to publish several events, you can return a Collection of events instead.
 */
package com.zx.spingbootmicrowebflux.event;
