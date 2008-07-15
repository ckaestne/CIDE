package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class JScriptVarStatement extends GenASTNode {
  public JScriptVarStatement(JScriptVarDeclarationList jScriptVarDeclarationList, ASTTextNode text339, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<JScriptVarDeclarationList>("jScriptVarDeclarationList", jScriptVarDeclarationList),
      new PropertyZeroOrOne<ASTTextNode>("text339", text339)
    }, firstToken, lastToken);
  }
  public JScriptVarStatement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new JScriptVarStatement(cloneProperties(),firstToken,lastToken);
  }
  public JScriptVarDeclarationList getJScriptVarDeclarationList() {
    return ((PropertyOne<JScriptVarDeclarationList>)getProperty("jScriptVarDeclarationList")).getValue();
  }
  public ASTTextNode getText339() {
    return ((PropertyZeroOrOne<ASTTextNode>)getProperty("text339")).getValue();
  }
}
