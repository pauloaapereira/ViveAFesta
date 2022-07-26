/*
 * Copyright 2022 Paulo Pereira
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.pp.viveafesta.domain.usecases

import com.pp.viveafesta.domain.Party
import com.pp.viveafesta.domain.repository.PartyRepository
import java.time.LocalDate
import java.time.LocalDateTime
import javax.inject.Inject

class AddParty @Inject constructor(
    private val partyRepository: PartyRepository
) {
    suspend operator fun invoke(party: Party) {
        partyRepository.addParty(party)
    }
}
