package com.example.nasapod.list.ui;

import java.lang.System;

@kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\f\u0012\b\u0012\u00060\u0002R\u00020\u00000\u0001:\u0002\u001f B\u0017\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u001c\u0010\u0017\u001a\u00020\u00182\n\u0010\u0019\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001a\u001a\u00020\u0016H\u0016J\u001c\u0010\u001b\u001a\u00060\u0002R\u00020\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0016H\u0016R \u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0086\u000e\u00a2\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006!"}, d2 = {"Lcom/example/nasapod/list/ui/APODListAdapter;", "Landroidx/recyclerview/widget/RecyclerView$Adapter;", "Lcom/example/nasapod/list/ui/APODListAdapter$ItemHolder;", "picasso", "Lcom/squareup/picasso/Picasso;", "podListView", "Lcom/example/nasapod/list/ui/PODListView;", "(Lcom/squareup/picasso/Picasso;Lcom/example/nasapod/list/ui/PODListView;)V", "apods", "", "Lcom/example/nasapod/list/vo/APODObject;", "getApods", "()Ljava/util/List;", "setApods", "(Ljava/util/List;)V", "clickListener", "Lcom/example/nasapod/list/ui/APODListAdapter$APODItemClickListener;", "getClickListener", "()Lcom/example/nasapod/list/ui/APODListAdapter$APODItemClickListener;", "setClickListener", "(Lcom/example/nasapod/list/ui/APODListAdapter$APODItemClickListener;)V", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "APODItemClickListener", "ItemHolder", "app_debug"})
public final class APODListAdapter extends androidx.recyclerview.widget.RecyclerView.Adapter<com.example.nasapod.list.ui.APODListAdapter.ItemHolder> {
    @org.jetbrains.annotations.NotNull()
    private java.util.List<com.example.nasapod.list.vo.APODObject> apods;
    @org.jetbrains.annotations.Nullable()
    private com.example.nasapod.list.ui.APODListAdapter.APODItemClickListener clickListener;
    private final com.squareup.picasso.Picasso picasso = null;
    private final com.example.nasapod.list.ui.PODListView podListView = null;
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.example.nasapod.list.vo.APODObject> getApods() {
        return null;
    }
    
    public final void setApods(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.nasapod.list.vo.APODObject> p0) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.example.nasapod.list.ui.APODListAdapter.APODItemClickListener getClickListener() {
        return null;
    }
    
    public final void setClickListener(@org.jetbrains.annotations.Nullable()
    com.example.nasapod.list.ui.APODListAdapter.APODItemClickListener p0) {
    }
    
    @org.jetbrains.annotations.NotNull()
    @java.lang.Override()
    public com.example.nasapod.list.ui.APODListAdapter.ItemHolder onCreateViewHolder(@org.jetbrains.annotations.NotNull()
    android.view.ViewGroup parent, int viewType) {
        return null;
    }
    
    @java.lang.Override()
    public int getItemCount() {
        return 0;
    }
    
    @java.lang.Override()
    public void onBindViewHolder(@org.jetbrains.annotations.NotNull()
    com.example.nasapod.list.ui.APODListAdapter.ItemHolder holder, int position) {
    }
    
    @javax.inject.Inject()
    public APODListAdapter(@org.jetbrains.annotations.NotNull()
    com.squareup.picasso.Picasso picasso, @org.jetbrains.annotations.NotNull()
    com.example.nasapod.list.ui.PODListView podListView) {
        super();
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u00a2\u0006\u0002\u0010\u0007J\u0016\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rJ\u0012\u0010\u000e\u001a\u00020\t2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0004H\u0016R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/example/nasapod/list/ui/APODListAdapter$ItemHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Landroid/view/View$OnClickListener;", "itemView", "Landroid/view/View;", "clickListener", "Lcom/example/nasapod/list/ui/APODListAdapter$APODItemClickListener;", "(Lcom/example/nasapod/list/ui/APODListAdapter;Landroid/view/View;Lcom/example/nasapod/list/ui/APODListAdapter$APODItemClickListener;)V", "bind", "", "item", "Lcom/example/nasapod/list/vo/APODObject;", "picasso", "Lcom/squareup/picasso/Picasso;", "onClick", "p0", "app_debug"})
    public final class ItemHolder extends androidx.recyclerview.widget.RecyclerView.ViewHolder implements android.view.View.OnClickListener {
        private final com.example.nasapod.list.ui.APODListAdapter.APODItemClickListener clickListener = null;
        
        public final void bind(@org.jetbrains.annotations.NotNull()
        com.example.nasapod.list.vo.APODObject item, @org.jetbrains.annotations.NotNull()
        com.squareup.picasso.Picasso picasso) {
        }
        
        @java.lang.Override()
        public void onClick(@org.jetbrains.annotations.Nullable()
        android.view.View p0) {
        }
        
        public ItemHolder(@org.jetbrains.annotations.NotNull()
        android.view.View itemView, @org.jetbrains.annotations.Nullable()
        com.example.nasapod.list.ui.APODListAdapter.APODItemClickListener clickListener) {
            super(null);
        }
    }
    
    @kotlin.Metadata(mv = {1, 1, 15}, bv = {1, 0, 3}, k = 1, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&\u00a8\u0006\b"}, d2 = {"Lcom/example/nasapod/list/ui/APODListAdapter$APODItemClickListener;", "", "onAPODItemClick", "", "obj", "Lcom/example/nasapod/list/vo/APODObject;", "position", "", "app_debug"})
    public static abstract interface APODItemClickListener {
        
        public abstract void onAPODItemClick(@org.jetbrains.annotations.NotNull()
        com.example.nasapod.list.vo.APODObject obj, int position);
    }
}