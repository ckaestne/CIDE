package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class DoStatement extends GenASTNode {
  public DoStatement(BlockOrSingleStatement blockOrSingleStatement, ASTStringNode findendcb, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<BlockOrSingleStatement>("blockOrSingleStatement", blockOrSingleStatement),
      new PropertyOne<ASTStringNode>("findendcb", findendcb)
    }, firstToken, lastToken);
  }
  public DoStatement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new DoStatement(cloneProperties(),firstToken,lastToken);
  }
  public BlockOrSingleStatement getBlockOrSingleStatement() {
    return ((PropertyOne<BlockOrSingleStatement>)getProperty("blockOrSingleStatement")).getValue();
  }
  public ASTStringNode getFindendcb() {
    return ((PropertyOne<ASTStringNode>)getProperty("findendcb")).getValue();
  }
}
