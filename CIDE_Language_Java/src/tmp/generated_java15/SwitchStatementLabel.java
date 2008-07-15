package tmp.generated_java15;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class SwitchStatementLabel extends GenASTNode {
  public SwitchStatementLabel(SwitchLabel switchLabel, ArrayList<BlockStatement> blockStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<SwitchLabel>("switchLabel", switchLabel),
      new PropertyZeroOrMore<BlockStatement>("blockStatement", blockStatement)
    }, firstToken, lastToken);
  }
  public SwitchStatementLabel(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new SwitchStatementLabel(cloneProperties(),firstToken,lastToken);
  }
  public SwitchLabel getSwitchLabel() {
    return ((PropertyOne<SwitchLabel>)getProperty("switchLabel")).getValue();
  }
  public ArrayList<BlockStatement> getBlockStatement() {
    return ((PropertyZeroOrMore<BlockStatement>)getProperty("blockStatement")).getValue();
  }
}
