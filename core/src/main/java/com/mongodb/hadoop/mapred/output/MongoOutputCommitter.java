/*
 * Copyright 2010-2013 10gen Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.mongodb.hadoop.mapred.output;

import org.apache.hadoop.mapred.JobContext;
import org.apache.hadoop.mapred.OutputCommitter;
import org.apache.hadoop.mapred.TaskAttemptContext;

import java.io.IOException;

/**
 * MongoOutputCommitter for legacy mapred API.
 *
 * This class is only used to support Pig, which creates the wrong type of
 * TaskAttemptContext for use with an OutputCommitter under Hadoop 1.x.
 */
public class MongoOutputCommitter extends OutputCommitter {
    private final com.mongodb.hadoop.output.MongoOutputCommitter delegate;

    public MongoOutputCommitter() {
        delegate = new com.mongodb.hadoop.output.MongoOutputCommitter();
    }

    @Override
    public void abortTask(final TaskAttemptContext taskContext)
      throws IOException {
        delegate.abortTask(taskContext);
    }

    @Override
    public void commitTask(final TaskAttemptContext taskContext)
      throws IOException {
        delegate.commitTask(taskContext);
    }

    @Override
    public boolean needsTaskCommit(final TaskAttemptContext taskContext)
      throws IOException {
        return delegate.needsTaskCommit(taskContext);
    }

    @Override
    public void setupJob(final JobContext jobContext) {
        delegate.setupJob(jobContext);
    }

    @Override
    public void setupTask(final TaskAttemptContext taskContext) {
        delegate.setupTask(taskContext);
    }
}
