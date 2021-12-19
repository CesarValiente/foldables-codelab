/*
 *
 *  * Copyright (C) 2021 Google Inc.
 *  *
 *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  * you may not use this file except in compliance with the License.
 *  * You may obtain a copy of the License at
 *  *
 *  *     http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  * Unless required by applicable law or agreed to in writing, software
 *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  * See the License for the specific language governing permissions and
 *  * limitations under the License.
 *
 */

package com.codelab.foldables.window_manager

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codelab.foldables.window_manager.databinding.ActivityMainBinding
import com.codelab.foldables.window_manager.embedding.ActivityA
import com.codelab.foldables.window_manager.navigation.NavigationActivity
import com.codelab.foldables.window_manager.preference.PreferenceActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.codelabBtn.setOnClickListener {
            val intent = Intent(this, CodelabActivity::class.java)
            startActivity(intent)
        }
        binding.flowBtn.setOnClickListener {
            val intent = Intent(this, FlowActivity::class.java)
            startActivity(intent)
        }
        binding.rxjavaBtn.setOnClickListener {
            val intent = Intent(this, RxJavaActivity::class.java)
            startActivity(intent)
        }
        binding.javaBtn.setOnClickListener {
            val intent = Intent(this, JavaActivity::class.java)
            startActivity(intent)
        }
        binding.embeddedActivitiesBtn.setOnClickListener {
            val intent = Intent(this, ActivityA::class.java)
            startActivity(intent)
        }
        binding.preferencesBtn.setOnClickListener {
            val intent = Intent(this, PreferenceActivity::class.java)
            startActivity(intent)
        }
        binding.navigationBtn.setOnClickListener {
            val intent = Intent(this, NavigationActivity::class.java)
            startActivity(intent)
        }
    }
}
