package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class LabeledStatement extends GenASTNode {
  public LabeledStatement(JavaIdentifier javaIdentifier, Statement statement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<JavaIdentifier>("javaIdentifier", javaIdentifier),
      new PropertyOne<Statement>("statement", statement)
    }, firstToken, lastToken);
  }
  public LabeledStatement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new LabeledStatement(cloneProperties(),firstToken,lastToken);
  }
  public JavaIdentifier getJavaIdentifier() {
    return ((PropertyOne<JavaIdentifier>)getProperty("javaIdentifier")).getValue();
  }
  public Statement getStatement() {
    return ((PropertyOne<Statement>)getProperty("statement")).getValue();
  }
}
