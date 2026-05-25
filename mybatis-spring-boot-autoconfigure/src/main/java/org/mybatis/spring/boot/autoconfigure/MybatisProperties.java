/*
 *    Copyright 2015-2025 the original author or authors.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */
package org.mybatis.spring.boot.autoconfigure;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Stream;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.scripting.LanguageDriver;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.AutoMappingUnknownColumnBehavior;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.DeprecatedConfigurationProperty;
import org.springframework.boot.context.properties.PropertyMapper;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * Configuration properties for MyBatis.
 *
 * @author Eddú Meléndez
 * @author Kazuki Shimizu
 */
@ConfigurationProperties(prefix = MybatisProperties.MYBATIS_PREFIX)
public class MybatisProperties {

    public static final String MYBATIS_PREFIX = "mybatis";

    private static final ResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();

    /**
     * Location of MyBatis xml config file.
     */
    private String configLocation;

    /**
     * Locations of MyBatis mapper files.
     */
    private String[] mapperLocations;

    /**
     * Packages to search type aliases. (Package delimiters are ",; \t\n")
     */
    private String typeAliasesPackage;

    /**
     * The super class for filtering type alias. If this not specifies, the MyBatis deal as type alias all classes that
     * searched from typeAliasesPackage.
     */
    private Class<?> typeAliasesSuperType;

    /**
     * Packages to search for type handlers. (Package delimiters are ",; \t\n")
     */
    private String typeHandlersPackage;

    /**
     * Indicates whether perform presence check of the MyBatis xml config file.
     */
    private boolean checkConfigLocation = false;

    /**
     * Execution mode for {@link org.mybatis.spring.SqlSessionTemplate}.
     */
    private ExecutorType executorType;

    /**
     * The default scripting language driver class. (Available when use together with mybatis-spring 2.0.2+)
     */
    private Class<? extends LanguageDriver> defaultScriptingLanguageDriver;

    /**
     * Externalized properties for MyBatis configuration.
     */
    private Properties configurationProperties;

    /**
     * A Configuration object for customize default settings. If {@link #configLocation} is specified, this property is
     * not used.
     */
    private CoreConfiguration configuration;

    /**
     * @since 1.1.0
     */
    public String getConfigLocation() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @since 1.1.0
     */
    public void setConfigLocation(String configLocation) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String[] getMapperLocations() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setMapperLocations(String[] mapperLocations) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getTypeHandlersPackage() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setTypeHandlersPackage(String typeHandlersPackage) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public String getTypeAliasesPackage() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setTypeAliasesPackage(String typeAliasesPackage) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @since 1.3.3
     */
    public Class<?> getTypeAliasesSuperType() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @since 1.3.3
     */
    public void setTypeAliasesSuperType(Class<?> typeAliasesSuperType) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public boolean isCheckConfigLocation() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setCheckConfigLocation(boolean checkConfigLocation) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public ExecutorType getExecutorType() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setExecutorType(ExecutorType executorType) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @since 2.1.0
     */
    public Class<? extends LanguageDriver> getDefaultScriptingLanguageDriver() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @since 2.1.0
     */
    public void setDefaultScriptingLanguageDriver(Class<? extends LanguageDriver> defaultScriptingLanguageDriver) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @since 1.2.0
     */
    public Properties getConfigurationProperties() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    /**
     * @since 1.2.0
     */
    public void setConfigurationProperties(Properties configurationProperties) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public CoreConfiguration getConfiguration() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public void setConfiguration(CoreConfiguration configuration) {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    public Resource[] resolveMapperLocations() {
        throw new UnsupportedOperationException("STUB: not implemented");
    }

    private Resource[] getResources(String location) {
        try {
            return resourceResolver.getResources(location);
        } catch (IOException e) {
            return new Resource[0];
        }
    }

    /**
     * The configuration properties for mybatis core module.
     *
     * @since 3.0.0
     */
    public static class CoreConfiguration {

        /**
         * Allows using RowBounds on nested statements. If allow, set the false. Default is false.
         */
        private Boolean safeRowBoundsEnabled;

        /**
         * Allows using ResultHandler on nested statements. If allow, set the false. Default is true.
         */
        private Boolean safeResultHandlerEnabled;

        /**
         * Enables automatic mapping from classic database column names A_COLUMN to camel case classic Java property names
         * aColumn. Default is false.
         */
        private Boolean mapUnderscoreToCamelCase;

        /**
         * When enabled, any method call will load all the lazy properties of the object. Otherwise, each property is loaded
         * on demand (see also lazyLoadTriggerMethods). Default is false.
         */
        private Boolean aggressiveLazyLoading;

        /**
         * Allows or disallows multiple ResultSets to be returned from a single statement (compatible driver required).
         * Default is true.
         */
        private Boolean multipleResultSetsEnabled;

        /**
         * Allows JDBC support for generated keys. A compatible driver is required. This setting forces generated keys to be
         * used if set to true, as some drivers deny compatibility but still work (e.g. Derby). Default is false.
         */
        private Boolean useGeneratedKeys;

        /**
         * Uses the column label instead of the column name. Different drivers behave differently in this respect. Refer to
         * the driver documentation, or test out both modes to determine how your driver behaves. Default is true.
         */
        private Boolean useColumnLabel;

        /**
         * Globally enables or disables any caches configured in any mapper under this configuration. Default is true.
         */
        private Boolean cacheEnabled;

        /**
         * Specifies if setters or map's put method will be called when a retrieved value is null. It is useful when you
         * rely on Map.keySet() or null value initialization. Note primitives such as (int,boolean,etc.) will not be set to
         * null. Default is false.
         */
        private Boolean callSettersOnNulls;

        /**
         * Allow referencing statement parameters by their actual names declared in the method signature. To use this
         * feature, your project must be compiled in Java 8 with -parameters option. Default is true.
         */
        private Boolean useActualParamName;

        /**
         * MyBatis, by default, returns null when all the columns of a returned row are NULL. When this setting is enabled,
         * MyBatis returns an empty instance instead. Note that it is also applied to nested results (i.e. collection and
         * association). Default is false.
         */
        private Boolean returnInstanceForEmptyRow;

        /**
         * Removes extra whitespace characters from the SQL. Note that this also affects literal strings in SQL. Default is
         * false.
         */
        private Boolean shrinkWhitespacesInSql;

        /**
         * Specifies the default value of 'nullable' attribute on 'foreach' tag. Default is false.
         */
        private Boolean nullableOnForEach;

        /**
         * When applying constructor auto-mapping, argument name is used to search the column to map instead of relying on
         * the column order. Default is false.
         */
        private Boolean argNameBasedConstructorAutoMapping;

        /**
         * Globally enables or disables lazy loading. When enabled, all relations will be lazily loaded. This value can be
         * superseded for a specific relation by using the fetchType attribute on it. Default is False.
         */
        private Boolean lazyLoadingEnabled;

        /**
         * Sets the number of seconds the driver will wait for a response from the database.
         */
        private Integer defaultStatementTimeout;

        /**
         * Sets the driver a hint as to control fetching size for return results. This parameter value can be override by a
         * query setting.
         */
        private Integer defaultFetchSize;

        /**
         * MyBatis uses local cache to prevent circular references and speed up repeated nested queries. By default
         * (SESSION) all queries executed during a session are cached. If localCacheScope=STATEMENT local session will be
         * used just for statement execution, no data will be shared between two different calls to the same SqlSession.
         * Default is SESSION.
         */
        private LocalCacheScope localCacheScope;

        /**
         * Specifies the JDBC type for null values when no specific JDBC type was provided for the parameter. Some drivers
         * require specifying the column JDBC type but others work with generic values like NULL, VARCHAR or OTHER. Default
         * is OTHER.
         */
        private JdbcType jdbcTypeForNull;

        /**
         * Specifies a scroll strategy when omit it per statement settings.
         */
        private ResultSetType defaultResultSetType;

        /**
         * Configures the default executor. SIMPLE executor does nothing special. REUSE executor reuses prepared statements.
         * BATCH executor reuses statements and batches updates. Default is SIMPLE.
         */
        private ExecutorType defaultExecutorType;

        /**
         * Specifies if and how MyBatis should automatically map columns to fields/properties. NONE disables auto-mapping.
         * PARTIAL will only auto-map results with no nested result mappings defined inside. FULL will auto-map result
         * mappings of any complexity (containing nested or otherwise). Default is PARTIAL.
         */
        private AutoMappingBehavior autoMappingBehavior;

        /**
         * Specify the behavior when detects an unknown column (or unknown property type) of automatic mapping target.
         * Default is NONE.
         */
        private AutoMappingUnknownColumnBehavior autoMappingUnknownColumnBehavior;

        /**
         * Specifies the prefix string that MyBatis will add to the logger names.
         */
        private String logPrefix;

        /**
         * Specifies which Object's methods trigger a lazy load. Default is [equals,clone,hashCode,toString].
         */
        private Set<String> lazyLoadTriggerMethods;

        /**
         * Specifies which logging implementation MyBatis should use. If this setting is not present logging implementation
         * will be autodiscovered.
         */
        private Class<? extends Log> logImpl;

        /**
         * Specifies VFS implementations.
         */
        private Class<? extends VFS> vfsImpl;

        /**
         * Specifies an sql provider class that holds provider method. This class apply to the type(or value) attribute on
         * sql provider annotation(e.g. @SelectProvider), when these attribute was omitted.
         */
        private Class<?> defaultSqlProviderType;

        /**
         * Specifies the TypeHandler used by default for Enum.
         */
        Class<? extends TypeHandler> defaultEnumTypeHandler;

        /**
         * Specifies the class that provides an instance of Configuration. The returned Configuration instance is used to
         * load lazy properties of deserialized objects. This class must have a method with a signature static Configuration
         * getConfiguration().
         */
        private Class<?> configurationFactory;

        /**
         * Specify any configuration variables.
         */
        private Properties variables;

        /**
         * Specifies the database identify value for switching query to use.
         */
        private String databaseId;

        public Boolean getSafeRowBoundsEnabled() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setSafeRowBoundsEnabled(Boolean safeRowBoundsEnabled) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Boolean getSafeResultHandlerEnabled() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setSafeResultHandlerEnabled(Boolean safeResultHandlerEnabled) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Boolean getMapUnderscoreToCamelCase() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setMapUnderscoreToCamelCase(Boolean mapUnderscoreToCamelCase) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Boolean getAggressiveLazyLoading() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setAggressiveLazyLoading(Boolean aggressiveLazyLoading) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        @DeprecatedConfigurationProperty(since = "3.0.4", reason = "The option is not used at MyBatis core module. It will be removed in the future. See https://github.com/mybatis/mybatis-3/pull/3238")
        public Boolean getMultipleResultSetsEnabled() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setMultipleResultSetsEnabled(Boolean multipleResultSetsEnabled) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Boolean getUseGeneratedKeys() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setUseGeneratedKeys(Boolean useGeneratedKeys) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Boolean getUseColumnLabel() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setUseColumnLabel(Boolean useColumnLabel) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Boolean getCacheEnabled() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setCacheEnabled(Boolean cacheEnabled) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Boolean getCallSettersOnNulls() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setCallSettersOnNulls(Boolean callSettersOnNulls) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Boolean getUseActualParamName() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setUseActualParamName(Boolean useActualParamName) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Boolean getReturnInstanceForEmptyRow() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setReturnInstanceForEmptyRow(Boolean returnInstanceForEmptyRow) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Boolean getShrinkWhitespacesInSql() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setShrinkWhitespacesInSql(Boolean shrinkWhitespacesInSql) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Boolean getNullableOnForEach() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setNullableOnForEach(Boolean nullableOnForEach) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Boolean getArgNameBasedConstructorAutoMapping() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setArgNameBasedConstructorAutoMapping(Boolean argNameBasedConstructorAutoMapping) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public String getLogPrefix() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setLogPrefix(String logPrefix) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Class<? extends Log> getLogImpl() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setLogImpl(Class<? extends Log> logImpl) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Class<? extends VFS> getVfsImpl() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setVfsImpl(Class<? extends VFS> vfsImpl) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Class<?> getDefaultSqlProviderType() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setDefaultSqlProviderType(Class<?> defaultSqlProviderType) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public LocalCacheScope getLocalCacheScope() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setLocalCacheScope(LocalCacheScope localCacheScope) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public JdbcType getJdbcTypeForNull() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setJdbcTypeForNull(JdbcType jdbcTypeForNull) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Set<String> getLazyLoadTriggerMethods() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setLazyLoadTriggerMethods(Set<String> lazyLoadTriggerMethods) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Integer getDefaultStatementTimeout() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setDefaultStatementTimeout(Integer defaultStatementTimeout) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Integer getDefaultFetchSize() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setDefaultFetchSize(Integer defaultFetchSize) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public ResultSetType getDefaultResultSetType() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setDefaultResultSetType(ResultSetType defaultResultSetType) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public ExecutorType getDefaultExecutorType() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setDefaultExecutorType(ExecutorType defaultExecutorType) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public AutoMappingBehavior getAutoMappingBehavior() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setAutoMappingBehavior(AutoMappingBehavior autoMappingBehavior) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public AutoMappingUnknownColumnBehavior getAutoMappingUnknownColumnBehavior() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setAutoMappingUnknownColumnBehavior(AutoMappingUnknownColumnBehavior autoMappingUnknownColumnBehavior) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Properties getVariables() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setVariables(Properties variables) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Boolean getLazyLoadingEnabled() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setLazyLoadingEnabled(Boolean lazyLoadingEnabled) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Class<?> getConfigurationFactory() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setConfigurationFactory(Class<?> configurationFactory) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public Class<? extends TypeHandler> getDefaultEnumTypeHandler() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setDefaultEnumTypeHandler(Class<? extends TypeHandler> defaultEnumTypeHandler) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public String getDatabaseId() {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void setDatabaseId(String databaseId) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }

        public void applyTo(Configuration target) {
            throw new UnsupportedOperationException("STUB: not implemented");
        }
    }
}
