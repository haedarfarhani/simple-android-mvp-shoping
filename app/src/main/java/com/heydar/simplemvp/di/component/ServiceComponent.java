package com.heydar.simplemvp.di.component;


import com.heydar.simplemvp.di.PerService;
import com.heydar.simplemvp.di.module.ServiceModule;
import com.heydar.simplemvp.service.SyncService;

import dagger.Component;

@PerService
@Component(dependencies = ApplicationComponent.class, modules = ServiceModule.class)
public interface ServiceComponent {

    void inject(SyncService service);

}
