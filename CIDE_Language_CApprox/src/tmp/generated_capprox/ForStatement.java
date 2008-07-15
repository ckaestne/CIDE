package tmp.generated_capprox;

import cide.gast.*;
import cide.gparser.*;
import cide.greferences.*;
import java.util.*;

public class ForStatement extends GenASTNode {
  public ForStatement(ASTStringNode findendcb, BlockOrSingleStatement blockOrSingleStatement, Token firstToken, Token lastToken) {
    super(new Property[] {
      new PropertyOne<ASTStringNode>("findendcb", findendcb),
      new PropertyOne<BlockOrSingleStatement>("blockOrSingleStatement", blockOrSingleStatement)
    }, firstToken, lastToken);
  }
  public ForStatement(Property[] properties, IToken firstToken, IToken lastToken) {
    super(properties,firstToken,lastToken);
  }
  public IASTNode deepCopy() {
    return new ForStatement(cloneProperties(),firstToken,lastToken);
  }
  public ASTStringNode getFindendcb() {
    return ((PropertyOne<ASTStringNode>)getProperty("findendcb")).getValue();
  }
  public BlockOrSingleStatement getBlockOrSingleStatement() {
    return ((PropertyOne<BlockOrSingleStatement>)getProperty("blockOrSingleStatement")).getValue();
  }
}
