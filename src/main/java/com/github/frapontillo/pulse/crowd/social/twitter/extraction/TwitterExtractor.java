/*
 * Copyright 2015 Francesco Pontillo
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.github.frapontillo.pulse.crowd.social.twitter.extraction;

import com.github.frapontillo.pulse.crowd.data.entity.Message;
import com.github.frapontillo.pulse.crowd.social.exception.SocialException;
import com.github.frapontillo.pulse.crowd.social.exception.TooComplexParametersSocialException;
import com.github.frapontillo.pulse.crowd.social.extraction.ExtractionParameters;
import com.github.frapontillo.pulse.crowd.social.extraction.IExtractor;
import com.github.frapontillo.pulse.util.PulseLogger;
import org.apache.logging.log4j.Logger;
import rx.Observable;

/**
 * @author Francesco Pontillo
 */
public class TwitterExtractor extends IExtractor {

    public static final String PLUGIN_NAME = "extractor-twitter";
    private static final Logger logger = PulseLogger.getLogger(TwitterExtractor.class);
    private static TwitterExtractorRunner runner = null;

    @Override public String getName() {
        return PLUGIN_NAME;
    }

    @Override public long getMaximumQueryParameters() {
        return 10;
    }

    @Override public boolean getSupportQuery() {
        return true;
    }

    @Override public boolean getSupportGeoLocation() {
        return true;
    }

    @Override public boolean getSupportFrom() {
        return true;
    }

    @Override public boolean getSupportTo() {
        return true;
    }

    @Override public boolean getSupportReference() {
        return true;
    }

    @Override public boolean getSupportSince() {
        return true;
    }

    @Override public boolean getSupportUntil() {
        return true;
    }

    @Override public boolean getSupportLanguage() {
        return true;
    }

    @Override public boolean getSupportLocale() {
        return true;
    }

    @Override public boolean mustSpecifyToOrFrom() {
        return false;
    }

    @Override public boolean validateParameters(ExtractionParameters parameters)
            throws SocialException {
        super.validateParameters(parameters);
        long paramCount = countNotNullParameters(parameters);
        long maxParamCount = getMaximumQueryParameters();
        if (paramCount > maxParamCount) {
            throw new TooComplexParametersSocialException(maxParamCount, paramCount);
        }
        return true;
    }

    private int countNotNullParameters(ExtractionParameters parameters) {
        int count = 0;
        if (parameters.getQuery() != null) {
            count = +1;
        }
        if (parameters.getGeoLocationBox() != null) {
            count += 1;
        }
        if (parameters.getFrom() != null) {
            count += 1;
        }
        if (parameters.getTo() != null) {
            count += 1;
        }
        if (parameters.getReferences() != null) {
            count += parameters.getReferences().size();
        }
        if (parameters.getFrom() != null) {
            count += 1;
        }
        if (parameters.getSince() != null) {
            count += 1;
        }
        if (parameters.getUntil() != null) {
            count += 1;
        }
        if (parameters.getLanguage() != null) {
            count += 1;
        }
        if (parameters.getLocale() != null) {
            count += 1;
        }
        return count;
    }

    private TwitterExtractorRunner getRunnerInstance() {
        if (runner == null) {
            runner = new TwitterExtractorRunner();
        }
        return runner;
    }

    @Override public Observable<Message> getMessages(final ExtractionParameters parameters) {
        // validate parameters
        try {
            validateParameters(parameters);
        } catch (SocialException e) {
            logger.error(e);
            e.printStackTrace();
            return Observable.empty();
        }

        return getRunnerInstance().getMessages(parameters);
    }
}
