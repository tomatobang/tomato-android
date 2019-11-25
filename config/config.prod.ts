'use strict';
const env = process.env;

exports.middleware = [ 'errorhandler', ];

exports.security = {
  csrf: {
    enable: false,
  },
};

exports.logger = {
  level: 'DEBUG',
  consoleLevel: 'INFO',
};

exports.static = {
  buffer: true,
  maxAge: 31536000,
};

exports.consul = {
  client: {
    host: {
      ip: env.CONSUL_IP,
      port: '8500'
    },
    server: {
      name: 'tomato-user',
      check: {
        path: '/api/ping'
      },
      tags: ['tomato']
    }
  }
};

exports.mongoose = {
  url: 'mongodb://' + env.DATABASE_MONGODB_USERNAME_PASSWORD + '@' + env.DATABASE_MONGODB_HOST_PORT + '/tomato-user',
  options: {},
};

exports.redis = {
  client: {
    port: env.REDIS_PORT || 6379,
    host: env.REDIS_HOST || '127.0.0.1',
    password: env.REDIS_PASSWORD || '',
    db: 0,
  },
};
