import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {UIView, UIRouterModule} from 'ui-router-ng2';
import {STATES, MyUIRouterConfig} from './routes';

import {TechsModule} from './techs';

import {MainComponent} from './main';
import {HeaderComponent} from './header';
import {TitleComponent} from './title';
import {FooterComponent} from './footer';

@NgModule({
  imports: [
    BrowserModule,
    UIRouterModule.forRoot({states: STATES, configClass: MyUIRouterConfig}),
    TechsModule
  ],
  declarations: [
    MainComponent,
    HeaderComponent,
    TitleComponent,
    FooterComponent
  ],
  bootstrap: [UIView]
})
export class AppModule {}
