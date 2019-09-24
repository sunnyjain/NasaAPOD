package com.example.nasapod.detail.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0001\u0019B\u0017\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u001c\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0010H\u0016J\u001c\u0010\u0015\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0010H\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/example/nasapod/detail/ui/APODDetailListApdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/nasapod/detail/ui/APODDetailListApdapter$APODItemViewHolder;", "picasso", "Lcom/squareup/picasso/Picasso;", "apodDetailView", "Lcom/example/nasapod/detail/ui/APODDetailView;", "(Lcom/squareup/picasso/Picasso;Lcom/example/nasapod/detail/ui/APODDetailView;)V", "apods", "", "Lcom/example/nasapod/list/vo/APODObject;", "getApods", "()Ljava/util/List;", "setApods", "(Ljava/util/List;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "APODItemViewHolder", "app_debug"})
public final class APODDetailListApdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.nasapod.detail.ui.APODDetailListApdapter.APODItemViewHolder> {
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.nasapod.list.vo.APODObject> apods;
    private final com.squareup.picasso.Picasso picasso = null;
    private final com.example.nasapod.detail.ui.APODDetailView apodDetailView = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.nasapod.list.vo.APODObject> getApods() {
        return null;
    }
    
    public final void setApods(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.nasapod.list.vo.APODObject> p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.example.nasapod.detail.ui.APODDetailListApdapter.APODItemViewHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.nasapod.detail.ui.APODDetailListApdapter.APODItemViewHolder holder, int position) {
    }
    
    @javax.inject.Inject()
    public APODDetailListApdapter(@org.jetbrains.annotations.NotNull()
    com.squareup.picasso.Picasso picasso, @org.jetbrains.annotations.NotNull()
    com.example.nasapod.detail.ui.APODDetailView apodDetailView) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n\u00a8\u0006\u000b"}, d2 = {"Lcom/example/nasapod/detail/ui/APODDetailListApdapter$APODItemViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Lcom/example/nasapod/detail/ui/APODDetailListApdapter;Landroid/view/View;)V", "bind", "", "item", "Lcom/example/nasapod/list/vo/APODObject;", "picasso", "Lcom/squareup/picasso/Picasso;", "app_debug"})
    public final class APODItemViewHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder {
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.example.nasapod.list.vo.APODObject item, @org.jetbrains.annotations.NotNull()
        com.squareup.picasso.Picasso picasso) {
        }
        
        public APODItemViewHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView) {
            super(null);
        }
    }
}