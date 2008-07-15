package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ContinueStatement extends GenASTNode {
  public ContinueStatement(JavaIdentifier javaIdentifier, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<JavaIdentifier>("javaIdentifier", javaIdentifier)
    }, firstToken, lastToken);
  }
  public ContinueStatement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ContinueStatement(cloneProperties(),firstToken,lastToken);
  }
  public JavaIdentifier getJavaIdentifier() {
    return ((PropertyZeroOrOne<JavaIdentifier>)getProperty("javaIdentifier")).getValue();
  }
}
