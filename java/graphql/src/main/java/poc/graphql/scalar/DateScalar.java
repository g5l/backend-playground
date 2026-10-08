package poc.graphql.scalar;

import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.StringValue;
import graphql.language.Value;
import graphql.schema.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

public final class DateScalar {

  public static final GraphQLScalarType INSTANCE = GraphQLScalarType.newScalar()
      .name("Date")
      .description("An ISO-8601 date, e.g. 2026-10-08")
      .coercing(new Coercing<LocalDate, String>() {

        // Java value -> wire. Runs when a Date is sent back in a response.
        @Override
        public String serialize(Object dataFetcherResult, GraphQLContext context, Locale locale)
            throws CoercingSerializeException {
          if (dataFetcherResult instanceof LocalDate date) {
            return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
          }
          throw new CoercingSerializeException(
              "Expected a LocalDate but got: " + dataFetcherResult);
        }
      })
      .build();

  private DateScalar() {
  }
}
