package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class Name extends GenASTNode {
  public Name(JavaIdentifier javaIdentifier, ArrayList<JavaIdentifier> javaIdentifier1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<JavaIdentifier>("javaIdentifier", javaIdentifier),
      new PropertyZeroOrMore<JavaIdentifier>("javaIdentifier1", javaIdentifier1)
    }, firstToken, lastToken);
  }
  public Name(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new Name(cloneProperties(),firstToken,lastToken);
  }
  public JavaIdentifier getJavaIdentifier() {
    return ((PropertyOne<JavaIdentifier>)getProperty("javaIdentifier")).getValue();
  }
  public ArrayList<JavaIdentifier> getJavaIdentifier1() {
    return ((PropertyZeroOrMore<JavaIdentifier>)getProperty("javaIdentifier1")).getValue();
  }
}
