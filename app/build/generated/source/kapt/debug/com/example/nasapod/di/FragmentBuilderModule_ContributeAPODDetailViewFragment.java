package com.example.nasapod.di;

import androidx.fragment.app.Fragment;
import com.example.nasapod.detail.ui.APODDetailView;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(
  subcomponents =
      FragmentBuilderModule_ContributeAPODDetailViewFragment.APODDetailViewSubcomponent.class
)
public abstract class FragmentBuilderModule_ContributeAPODDetailViewFragment {
  private FragmentBuilderModule_ContributeAPODDetailViewFragment() {}

  @Binds
  @IntoMap
  @FragmentKey(APODDetailView.class)
  abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(
      APODDetailViewSubcomponent.Builder builder);

  @Subcomponent
  public interface APODDetailViewSubcomponent extends AndroidInjector<APODDetailView> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<APODDetailView> {}
  }
}
