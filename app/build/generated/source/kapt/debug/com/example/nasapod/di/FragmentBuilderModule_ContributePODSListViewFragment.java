package com.example.nasapod.di;

import androidx.fragment.app.Fragment;
import com.example.nasapod.list.ui.PODListView;
import dagger.Binds;
import dagger.Module;
import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;

@Module(
  subcomponents = FragmentBuilderModule_ContributePODSListViewFragment.PODListViewSubcomponent.class
)
public abstract class FragmentBuilderModule_ContributePODSListViewFragment {
  private FragmentBuilderModule_ContributePODSListViewFragment() {}

  @Binds
  @IntoMap
  @FragmentKey(PODListView.class)
  abstract AndroidInjector.Factory<? extends Fragment> bindAndroidInjectorFactory(
      PODListViewSubcomponent.Builder builder);

  @Subcomponent
  public interface PODListViewSubcomponent extends AndroidInjector<PODListView> {
    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<PODListView> {}
  }
}
