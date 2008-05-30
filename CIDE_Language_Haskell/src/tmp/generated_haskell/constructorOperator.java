package tmp.generated_haskell;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class constructorOperator extends GenASTNode {
  public constructorOperator(ModuleNaamPrefix moduleNaamPrefix, constructorOperatorMain constructorOperatorMain, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyZeroOrOne<ModuleNaamPrefix>("moduleNaamPrefix", moduleNaamPrefix),
      new PropertyOne<constructorOperatorMain>("constructorOperatorMain", constructorOperatorMain)
    }, firstToken, lastToken);
  }
  public constructorOperator(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public ASTNode deepCopy() {
    return new constructorOperator(cloneProperties(),firstToken,lastToken);
  }
  public ModuleNaamPrefix getModuleNaamPrefix() {
    return ((PropertyZeroOrOne<ModuleNaamPrefix>)getProperty("moduleNaamPrefix")).getValue();
  }
  public constructorOperatorMain getConstructorOperatorMain() {
    return ((PropertyOne<constructorOperatorMain>)getProperty("constructorOperatorMain")).getValue();
  }
}
