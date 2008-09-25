package tmp.generated_haskell;

import java.util.ArrayList;

import cide.gast.ASTStringNode;
import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;
import cide.gast.PropertyOne;
import cide.gast.PropertyZeroOrMore;
import cide.gparser.Token;

public class ModuleNaamPrefix extends GenASTNode {
  public ModuleNaamPrefix(ASTStringNode constructor_id, ArrayList<ASTStringNode> constructor_id1, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("constructor_id", constructor_id),
      new PropertyZeroOrMore<ASTStringNode>("constructor_id1", constructor_id1)
    }, firstToken, lastToken);
  }
  public ModuleNaamPrefix(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ModuleNaamPrefix(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getConstructor_id() {
    return ((PropertyOne<ASTStringNode>)getProperty("constructor_id")).getValue();
  }
  public ArrayList<ASTStringNode> getConstructor_id1() {
    return ((PropertyZeroOrMore<ASTStringNode>)getProperty("constructor_id1")).getValue();
  }
}
