package tmp.generated_javacc;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class VariableDeclaratorId extends GenASTNode {
  public VariableDeclaratorId(JavaIdentifier javaIdentifier, ArrayList<ASTTextNode> text498, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<JavaIdentifier>("javaIdentifier", javaIdentifier),
      new PropertyZeroOrMore<ASTTextNode>("text498", text498)
    }, firstToken, lastToken);
  }
  public VariableDeclaratorId(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new VariableDeclaratorId(cloneProperties(),firstToken,lastToken);
  }
  public JavaIdentifier getJavaIdentifier() {
    return ((PropertyOne<JavaIdentifier>)getProperty("javaIdentifier")).getValue();
  }
  public ArrayList<ASTTextNode> getText498() {
    return ((PropertyZeroOrMore<ASTTextNode>)getProperty("text498")).getValue();
  }
}
