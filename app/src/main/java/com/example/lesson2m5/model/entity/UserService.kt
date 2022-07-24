package com.example.lesson2m5.model.entity

import com.github.javafaker.Faker

typealias UserListener = (users: List<User>) -> Unit

class UserService {

    private var users: MutableList<User> = mutableListOf()

    private val listener = mutableSetOf<UserListener>()

    init {
          val faker = Faker.instance()
          Images.shuffle()
          users = (1..42).map{User(
              id = it.toInt(),
              name = faker.name().name(),
              photo = Images[it % Images.size]
          )}.toMutableList()
    }

    fun getUsers(): List<User> = users

    fun deleteUsers(user: User) {
        val indexToDelete = users.indexOfFirst { it.id == user.id }
        if (indexToDelete != -1) {
            users.removeAt(indexToDelete)
            notifyChanged()
        }
    }

        fun addListener(listeners: UserListener) {
            listener.add(listeners)
            listeners.invoke(users)
        }

        fun removeListener(listeners: UserListener) {
            listener.remove(listeners)
        }

        private fun notifyChanged() {
            listener.forEach { it.invoke(users) }
        }



    companion object{
        private val Images = mutableListOf(
            "https://pixelbox.ru/wp-content/uploads/2021/06/ava-steam-anime-tyan-27.jpg",
            "https://pixelbox.ru/wp-content/uploads/2021/06/ava-steam-anime-tyan-19.jpg",
            "https://images.hdqwalls.com/download/anime-girl-cute-rainbows-and-lolipop-j4-2932x2932.jpg",
            "https://kartinkin.net/uploads/posts/2021-07/1625504503_53-kartinkin-com-p-milie-anime-pikchi-anime-krasivo-58.png",
            "https://pic.rutubelist.ru/user/65/98/65986816a5d3981c139a458e5c461db8.jpg",
            "https://fanibani.ru/images/wp-content/uploads/2021/02/image186-32.jpg",
            "https://otvet.imgsmail.ru/download/260461445_22cbfb55c970b39fd33af1a451e99fff_800.jpg",
            "https://i.pinimg.com/originals/9c/a6/6b/9ca66b1d30e7d613aba05d5ebe3d3dd7.jpg",
            "https://i.imgflip.com/154sjp.jpg",
            "https://i.pinimg.com/originals/55/6d/c4/556dc408032368dadf18d0bf9242496c.jpg",
            "https://i.pinimg.com/736x/67/fd/67/67fd67b9eb8c036f4067a72e8b567a98--fairytail-shrek.jpg",
            "https://www.meme-arsenal.com/memes/16e7792cc8fc5fd01f51c2ffddfc62bc.jpg",
            "https://i-a.d-cd.net/6f7d1804k290-1920.jpg",
        )
    }

}