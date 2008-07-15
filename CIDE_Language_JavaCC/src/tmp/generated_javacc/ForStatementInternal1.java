package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ForStatementInternal1 extends ForStatementInternal {
  public ForStatementInternal1(Type type, JavaIdentifier javaIdentifier, Expression expression, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<Type>("type", type),
      new PropertyOne<JavaIdentifier>("javaIdentifier", javaIdentifier),
      new PropertyOne<Expression>("expression", expression)
    }, firstToken, lastToken);
  }
  public ForStatementInternal1(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ForStatementInternal1(cloneProperties(),firstToken,lastToken);
  }
  public Type getType() {
    return ((PropertyOne<Type>)getProperty("type")).getValue();
  }
  public JavaIdentifier getJavaIdentifier() {
    return ((PropertyOne<JavaIdentifier>)getProperty("javaIdentifier")).getValue();
  }
  public Expression getExpression() {
    return ((PropertyOne<Expression>)getProperty("expression")).getValue();
  }
}
