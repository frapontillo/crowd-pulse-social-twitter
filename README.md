crowd-pulse-social-twitter
==========================

Twitter social extractor implementation for Crowd Pulse.

--------------------------

This module contains several plugins (`extractor-twitter`, `reply-extractor-twitter`, 
`profiler-twitter`, `twitter-profile-grapher`) that need a `twitter4j.properties` file in the class 
loader accessible resources directory.

It must hold the following keys and related values:

- `twitter.consumerKey`, your Twitter consumer key
- `twitter.consumerSecret`, your Twitter consumer secret
- `twitter.accessToken`, your Twitter access token
- `twitter.accessTokenSecret`, your Twitter access token secret
  
## License

```
  Copyright 2015 Francesco Pontillo

  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at

      http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.

```