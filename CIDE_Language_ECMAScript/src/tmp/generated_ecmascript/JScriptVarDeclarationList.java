package tmp.generated_ecmascript;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class JScriptVarDeclarationList extends GenASTNode {
  public JScriptVarDeclarationList(JScriptVarDeclaration jScriptVarDeclaration, ArrayList<JScriptVarDeclaration> jScriptVarDeclaration1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<JScriptVarDeclaration>("jScriptVarDeclaration", jScriptVarDeclaration),
      new PropertyZeroOrMore<JScriptVarDeclaration>("jScriptVarDeclaration1", jScriptVarDeclaration1)
    }, firstToken, lastToken);
  }
  public JScriptVarDeclarationList(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new JScriptVarDeclarationList(cloneProperties(),firstToken,lastToken);
  }
  public JScriptVarDeclaration getJScriptVarDeclaration() {
    return ((PropertyOne<JScriptVarDeclaration>)getProperty("jScriptVarDeclaration")).getValue();
  }
  public ArrayList<JScriptVarDeclaration> getJScriptVarDeclaration1() {
    return ((PropertyZeroOrMore<JScriptVarDeclaration>)getProperty("jScriptVarDeclaration1")).getValue();
  }
}
