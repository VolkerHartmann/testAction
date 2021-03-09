/*
 * Copyright 2019 Karlsruhe Institute of Technology.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.kit.datamanager.annotations;

import edu.kit.datamanager.validator.ElasticsearchValidator;
import java.lang.annotation.Documented;
import static java.lang.annotation.ElementType.FIELD;
import java.lang.annotation.Retention;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 *
 * @author jejkal
 */
@Target({FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = ElasticsearchValidator.class)
@Documented
public @interface ElasticsearchURL{

  String message() default "Provided elasticsearch URL invalid. A valid URL to a running elasticsearch server instance is required. (e.g.: http://localhost:9200)";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};

}
