'use strict';
import { Application } from 'egg';

export default (app: Application) => {
  const { router, controller } = app;

  router.get('/', controller.home.index);
  router.get('/api/ping', controller.home.index);
  /** TEST: unregister consul */
  // router.get('/api/unregister', controller.home.unregister);

  /**
    * 用户类
    */
  router.get('/api/user', controller.user.list);
  router.get('/api/user/auth', controller.user.auth);
  router.get('/api/user/searchUsers', controller.user.searchUsers);
  router.get('/api/user/:id', controller.user.findById);
  router.post('/api/user', controller.user.create);
  router.del('/api/user/:id', controller.user.deleteById);
  router.post('/email_username/verify', controller.user.emailUserNameVerify);
  router.post('/api/login', controller.user.login);
  router.get('/api/logout', controller.user.logout);
  router.post('/api/user/headimg', controller.user.updateHeadImg);
  router.post('/api/user/sex', controller.user.updateSex);
  router.post('/api/user/displayname', controller.user.updateDisplayName);
  router.post('/api/user/email', controller.user.updateEmail);
  router.post('/api/user/location', controller.user.updateLocation);
  router.post('/api/user/bio', controller.user.updateBio);
  router.post('/api/user/changepwd', controller.user.changePWD);
  router.post('/api/user/:id', controller.user.updateById);

  /**
   * 社交类
   */
  router.get('/api/user_friend', controller.userFriend.getUserFriends);

  router.post(
    '/api/user_friend/request_add_friend',
    controller.userFriend.requestAddFriend
  );
  router.post(
    '/api/user_friend/response_add_friend',
    controller.userFriend.responseAddFriend
  );

};
