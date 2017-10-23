import {Injectable} from '@angular/core';
import {UIRouter, Ng2StateDeclaration} from 'ui-router-ng2';

import {MainComponent} from './main';

export const STATES: Ng2StateDeclaration[] = [
  {
    name: 'App',
    url: '/',
    component: MainComponent
  }
];

@Injectable()
export class MyUIRouterConfig {
  configure(uiRouter: UIRouter) {
    uiRouter.urlRouterProvider.otherwise('App');
  }
}
